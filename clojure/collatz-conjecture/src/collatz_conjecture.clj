(ns collatz-conjecture)

(defn collatz [n]
  (cond
    (= n 1) 0
    (even? n) (+ 1 (collatz (/ n 2)))
    :else (+ 1 (collatz (inc (* 3 n))))))


(defn collatz-recur [n]
  (if (< n 1)
    (throw (IllegalArgumentException. "Number must be positive!"))
    (loop [n n, acc 0]
      (cond
        (= n 1) acc
        (even? n) (recur (/ n 2) (inc acc))
        :else (recur (inc (* 3 n)) (inc acc))))))
