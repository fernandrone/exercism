(ns reverse-string)
(require '[clojure.string :as string])

(defn reverse-index-list
  "Given a string, returns a list of sequential natural numbers [N, N-1, N-2, ..., 0], where N equals the total number of characters of that string"
  [s]
  (range (dec (count s)) -1 -1))

(defn reverse-string [s]
  (string/join (map #(nth s %) (reverse-index-list s))))
