(ns rna-transcription
  (:require [clojure.string :as str]))

(defn- dna->rna
  "Converts a DNA nucleotide string to a RNA nucleotide string."
  [nucleotide]
  (case nucleotide
    "G" "C"
    "C" "G"
    "T" "A"
    "A" "U"))

(defn- dna?
  "Returns true if the nucleotide is a valid DNA strand:
   a string of value G, C, T or A."
  [nucleotide]
  (case nucleotide
    "G" true
    "C" true
    "T" true
    "A" true
    false))

(defn- dna->coll
  "Converts a DNA string into a lazy sequence of its individual
   DNA nucleotides."
  [dna]
  (re-seq #"\w{1}" dna))

(defn to-rna [dna]
  {:pre [(every? dna? (dna->coll dna))]}
  (->> (dna->coll dna)
       (map dna->rna)
       (str/join)))
