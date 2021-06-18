(ns crypto-square
  (:require [clojure.string :as str]))

(defn normalize-plaintext [s]
  (str/replace s #"[^a-zA-Z]" ""))

(defn square-size [s]
  (->> (iterate inc 1)
       (drop-while #(< (* % %) (count s)))
       (first)))

(defn plaintext-segments [] ;; <- arglist goes here
  ;; your code goes here
  )

(defn ciphertext [] ;; <- arglist goes here
  ;; your code goes here
  )

(defn normalize-ciphertext [] ;; <- arglist goes here
  ;; your code goes here
  )
