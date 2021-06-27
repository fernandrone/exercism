(ns rna-transcription
  (:require [clojure.string :as str]))


(def ^:private dna->rna
  {\G \C
   \C \G
   \T \A
   \A \U})

(defn to-rna [dna]
  {:post [(= (count dna) (count %))]}
  (str/join (map dna->rna (char-array dna))))
