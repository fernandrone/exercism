(ns series)

(defn substring [string start size] (subs string start (+ start size)))

(defn slices [string length]
  (let [string-len (count string)
        series-len length]
    (cond
      (= string-len 0) []
      (= series-len 0) [""]
      :else (map
             #(substring string % series-len)
             (range (inc (- string-len series-len)))))))
