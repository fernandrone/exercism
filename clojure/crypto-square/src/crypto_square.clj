(ns crypto-square
  (:require [clojure.string :as str]))

(defn normalize-plaintext [s]
  (-> (str/lower-case s)
      (str/replace #"[^0-9a-zA-Z]" "")))

(defn square-size [s]
  (->> (iterate inc 1)
       (drop-while #(< (* % %) (count (normalize-plaintext s))))
       (first)))

(defn plaintext-segments [s]
  (->> (normalize-plaintext s)
       (re-seq (re-pattern (format ".{1,%d}" (square-size s))))))

(defn ciphertext [s]
  (let [s (plaintext-segments s)]
    (loop [square s, acc ""]
      (if (every? empty? square)
        acc
        (recur
         (map #(subs % (min 1 (count %))) square)
         (str/join (conj (map #(get % 0) square) acc)))))))

(defn row-size [s]
  (->> (iterate inc 1)
       (drop-while #(< (* % (inc %)) (count (normalize-plaintext s))))
       (first)))

(defn normalize-ciphertext [s]
  (let [c      (square-size s), r (row-size s)
        len    (count (normalize-plaintext s))
        cipher (ciphertext s)
        split (- (* r c) len)
        begin  (->> cipher
                    (re-seq (re-pattern (format ".{1,%d}" r)))
                    (take (- c split)))
        end    (->> cipher
                    (str/reverse)
                    (re-seq (re-pattern (format ".{1,%d}" (dec r))))
                    (take split)
                    (map str/reverse)
                    (reverse))]
    (->> (concat begin end)
         (str/join " "))))
