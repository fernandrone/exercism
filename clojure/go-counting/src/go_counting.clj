(ns go-counting)

(def empty-territories
  {:black-territory #{}
   :white-territory #{}
   :null-territory  #{}})

(def empty-territory
  {:stones #{} :owner nil})

(defn- abs [n] (max n (- n)))

(defn- adjacent? [[x1 y1] [x2 y2]]
  (cond
    (and (= x1 x2) (= (abs (- y2 y1)) 1)) true
    (and (= y1 y2) (= (abs (- x2 x1)) 1)) true
    :else false))

(defn- any-adjacent? [list [x y]]
  (some true? (map #(adjacent? [x y] %) list)))

(defn- get-piece [grid [x y]]
  (-> grid
      (get y)
      (get x)))

(defn- get-board [grid]
  (let [x-len (count (first grid))
        y-len (count grid)]
    (for [x (range x-len)
          y (range y-len)]
      (vector x y))))

(defn- filter-positions [grid positions ch]
  (filter #(= (get-piece grid %) ch) positions))

(defn- filter-empty-positions [grid positions]
  (filter-positions grid positions \space))

(defn- filter-black-positions [grid positions]
  (filter-positions grid positions \B))

(defn- filter-white-positions [grid positions]
  (filter-positions grid positions \W))

(defn- within-grid? [grid [x y]]
  (let [board (get-board grid)]
    (some #(= [x y] %) board)))

(defn- get-adjacent-territory [grid board [x y]]
  (let [empty-positions (filter-empty-positions grid board)]
    (loop [starting-territory #{}]
      (let [adjacent-territory (reduce (fn [new-territory pos]
                                         (let [merged-territory (into starting-territory new-territory)
                                               is-pos-adjacent? (some true? (map #(adjacent? pos %) merged-territory))]
                                           (if is-pos-adjacent?
                                             (conj merged-territory pos)
                                             merged-territory)))
                                       #{[x y]} empty-positions)
            found-more-territory? (> (count adjacent-territory) (count starting-territory))]
        (if found-more-territory?
          (recur adjacent-territory)
          adjacent-territory)))))

(defn- get-owner [grid board stones]
  (let [black-pieces (filter-black-positions grid board)
        white-pieces (filter-white-positions grid board)]
    (cond
      (and (some true? (map #(any-adjacent? stones %) black-pieces))
           (some true? (map #(any-adjacent? stones %) white-pieces))) nil
      (some true? (map #(any-adjacent? stones %) black-pieces))       :black
      (some true? (map #(any-adjacent? stones %) white-pieces))       :white
      :else nil)))

(defn territory [grid [x y]]
  (if (not (within-grid? grid [x y]))
    (throw (IllegalArgumentException. (format "Invalid input: position [%d %d] outside grid" x y)))
    (let [board (get-board grid)
          piece (get-piece grid [x y])]
      (if (= piece \space)
        (let [stones (get-adjacent-territory grid board [x y])
              owner  (get-owner grid board stones)]
          (assoc empty-territory :stones stones :owner owner))
        empty-territory))))

(defn territories [grid]
  (let [board (get-board grid)
        empty-positions (filter-empty-positions grid board)
        vector-of-stones (map #(territory grid %) empty-positions)]
    (reduce (fn [territories-map territory-map]
              (case (:owner territory-map)
                (:black) (update territories-map :black-territory into (:stones territory-map))
                (:white) (update territories-map :white-territory into (:stones territory-map))
                (nil)    (update territories-map :null-territory  into (:stones territory-map))))
            empty-territories vector-of-stones)))
