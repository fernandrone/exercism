(ns allergies)

(def tests
  {1 :eggs
   2 :peanuts
   4 :shellfish
   8 :strawberries
   16 :tomatoes
   32 :chocolate
   64 :pollen
   128 :cats})

(defn- in?
  "True if coll contains elm"
  [coll elm]
  (some #(= elm %) coll))

(defn- pow
  "x^n"
  [x n]
  (if (>= n 0)
    (->> x (repeat n) (reduce *))
    (->> (pow (/ 1 x) (- n)))))

;; update: there's a more direct algorithm here 
;; https://math.stackexchange.com/questions/1553894/is-there-an-algorithm-to-split-a-number-into-the-sum-of-powers-of-2
(defn- factors-of-two
  "Return a vector containing the factors of two that compose a number"
  [n]
  (let [exps (mapv #(pow 2 %) (range 0 12))] ; [ 1 ... 2048 ]
    (loop [n n, acc []]
      (cond
        (= n 0) []
        (in? exps n) (conj acc n)
        :else (let [factor (last (filterv #(<= % n) exps))]
                (recur (- n factor) (conj acc factor)))))))

(defn allergies [n]
  (->> (factors-of-two n)
       (filterv #(<= % 128))
       (select-keys tests)
       (reverse)
       (vals)
       (vec)))

(defn allergic-to? [n item]
  (in? (allergies n) item))
