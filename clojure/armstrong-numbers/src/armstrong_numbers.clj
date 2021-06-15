(ns armstrong-numbers)

(defn pow
  "x^n"
  [x n]
  (->> x (repeat n) (reduce *)))

(defn armstrong? [num]
  (let [d (->> (iterate #(quot % 10) num) ; get quotient by 10 / 100 / 1000 / etc.
               (take-while pos?)          ; take all values > 0
               (map #(mod % 10)))         ; get modulus by 10, result are digits
        n (count d)]
    (->> (map #(pow % n) d)
         (apply +)
         (= num))))
