(ns go-counting)

(def empty-territories
  {:black-territory #{}
   :white-territory #{}
   :null-territory  #{}})

(defn territory [grid [x y]])


;; [[0 0][0 1]
;;  [1 0] [1 1]]
(defn- board-positions [x-len y-len]
  (for [x (range x-len)
        y (range y-len)]
    (vector x y)))

(defn- get-piece [grid [x y]]
  (-> grid
      (get y)
      (get x)))

(defn territories [grid]
  (let [x-len (count (first grid))
        y-len (count grid)
        board (board-positions x-len y-len)]
    (println board)
    (reduce (fn [territories-map pos]
              (let [[x y] pos
                    piece (get-piece grid [x y])]
                (println x y piece)
                (when (= piece \space)
                  ;; update this logic
                  ;; to check neighbors for black or white pieces
                  ;; possibly recursively
                  ;; will need a get-neighbors function
                  (update territories-map :black-territory conj pos))))
            empty-territories board)))
