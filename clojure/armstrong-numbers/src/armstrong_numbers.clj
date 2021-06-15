(ns armstrong-numbers)

(defn pow
  "x^n"
  [x n]
  (->> x (repeat n) (reduce *)))

(defn armstrong? [number]
  (let [d (->> (iterate #(quot % 10) number)
               (take-while pos?)
               (map #(mod % 10)))
        n (count d)]
    (->> (map #(pow % n) d)
         (apply +)
         (= number))))
