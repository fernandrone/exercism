(ns allergies)

(def ^:private allergens
  [[1 :eggs]
   [2 :peanuts]
   [4 :shellfish]
   [8 :strawberries]
   [16 :tomatoes]
   [32 :chocolate]
   [64 :pollen]
   [128 :cats]])

(defn- in?
  "True if coll contains elm"
  [coll elm]
  (some #(= elm %) coll))

(defn allergies [n]
  (for [[v a] allergens
        :when (not (zero? (bit-and v n)))]
    a))

(defn allergic-to? [n item]
  (in? (allergies n) item))
