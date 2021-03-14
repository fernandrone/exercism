(ns nucleotide-count)

(def valid-nucleotides [\A \C \G \T])

(defn count-of-nucleotide-in-strand [nucleotide strand]
  (if (.contains valid-nucleotides nucleotide)
    (count (filter #(= nucleotide %) strand))
    (throw (Exception. (str nucleotide " is not a valid nucleotide!")))))

; nucleotide-counts uses reduce to build a map out of the 'valid-nucleotides' list. This
; is certainly not the most simple solution, but I like how it is very extensible, as it
; cycles through a global reference instead of hardcoding a map and avoids duplication.
;
; Although one could argue that nucleotides are unlikely to change in favor of a less
; generic but more simple approach, but in that case could have simply used the
; 'frequencies' function, which would have dismissed the need of the
; 'count-of-nucleotide-in-strand' helper function and made the whole exercise trival :)
(defn nucleotide-counts [strand]
  (reduce #(assoc %1 %2 (count-of-nucleotide-in-strand %2 strand))
          {}
          valid-nucleotides))
