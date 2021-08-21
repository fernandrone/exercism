(ns run-length-encoding)

(defn- encode-elem
  [[first & remaining :as element]]
  (cond->> first
    (some? remaining) (str (count element))))

(defn run-length-encode
  "Encodes a string with run-length-encoding"
  [plain-text]
  (->> (partition-by identity plain-text)
       (map encode-elem)
       (apply str)))

;; When applied to an encoded string with 're-seq' this pattern splits it into
;; arrays of three elements: the first is the matcr, and the second and third
;; are the first and second capture group. They correspond to the number of
;; repeatitions '(\d*)' (which can be an empty string) and the character to be
;; repeated '([a-zA-Z \s])'.
(def ^:private decode-pattern
  #"(\d*)([a-zA-Z\s])")

(defn- decode-elem
  [[_ re ch]]
  (cond->> ch
    (seq re) (repeat (Integer/parseInt re))))

(defn run-length-decode
  "Decodes a run-length-encoded string"
  [cipher-text]
  (->> (re-seq decode-pattern cipher-text)
       (mapcat decode-elem)
       (apply str)))
