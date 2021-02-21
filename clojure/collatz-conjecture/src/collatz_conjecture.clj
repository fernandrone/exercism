(ns collatz-conjecture)

(defn collatz [n]
  (cond
    (= n 1) 0
    (even? n) (+ 1 (collatz (/ n 2)))
    :else (+ 1 (collatz (inc (* 3 n))))))
