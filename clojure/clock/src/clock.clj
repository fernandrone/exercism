(ns clock)

(defn clock->string [{:keys [hours minutes]}]
  (format "%02d:%02d" hours minutes))

(defn clock [hours minutes]
  {:minutes (mod minutes 60)
   :hours   (cond-> (+ hours (quot minutes 60)) ;; first sum hours and minutes/60
              (< minutes 0) dec                 ;; if minutes < 0, decrement hours by 1
              :always (mod 24))})               ;; then get modulus by 24

(defn add-time [{:keys [hours minutes]} time]
  (clock hours (+ minutes time)))
