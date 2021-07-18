(ns run-length-encoding
  (:require [clojure.string :as str]))
(defn- encode-elem
  [[first & remaining :as element]]
  (if (nil? remaining)
    first
    (str (count element) first)))

(defn run-length-encode
  "Encodes a string with run-length-encoding"
  [plain-text]
  (->> (partition-by identity plain-text)
       (map encode-elem)
       (str/join)))

;; When applied to an encoded string with 're-seq' this pattern splits it into
;; arrays of three elements: the first is the matchr, and the second and third
;; are the first and second capture group. They correspond to the number of
;; repeatitions '(\d*)' (which can be an empty string) and the character to be
;; repeated '([a-zA-Z \s])'.
(def ^:private decode-pattern
  #"(\d*)([a-zA-Z\s])")

(defn- decode-elem
  [[_ re ch]]
  (if (empty? re)
    ch
    (str/join (repeat (Integer/parseInt re) ch))))

(defn run-length-decode
  "Decodes a run-length-encoded string"
  [cipher-text]
  (->> (re-seq decode-pattern cipher-text)
       (map decode-elem)
       (str/join)))
