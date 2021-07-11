(ns run-length-encoding
  (:require [clojure.string :as str]))

(defn run-length-encode
  "Encodes a string with run-length-encoding"
  [plain-text]
  (->> (re-seq #"(.)\1*" plain-text)
       (map first)
       (map #(str (count %) (first %)))
       (map #(str/replace % #"1([a-zA-Z\s]+)" "$1"))
       (str/join)))


(defn- str->int
  "Converts string to integer. As this function is used in
  multiplications, if the string is empty it returns 1."
  [s]
  (if (empty? s)
    1
    (Integer/parseInt s)))

(defn run-length-decode
  "Decodes a run-length-encoded string"
  [cipher-text]
  (let [matches (re-seq #"\d*[a-zA-Z\s]" cipher-text)
        re      (->> (map #(re-find #"\d+" %) matches)
                     (map #(str->int %)))
        ch      (map #(re-find #"[a-zA-Z\s]+" %) matches)]
    (->> (apply map vector [re ch])
         (map #(repeat (first %) (second %)))
         (map str/join)
         (str/join))))
