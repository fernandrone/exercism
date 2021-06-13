(ns queen-attack)

(def empty-board
  (str "_ _ _ _ _ _ _ _\n"
       "_ _ _ _ _ _ _ _\n"
       "_ _ _ _ _ _ _ _\n"
       "_ _ _ _ _ _ _ _\n"
       "_ _ _ _ _ _ _ _\n"
       "_ _ _ _ _ _ _ _\n"
       "_ _ _ _ _ _ _ _\n"
       "_ _ _ _ _ _ _ _\n"))

(defn- replace-at
  "Given a string, an index and a replacement character, replaces the character
   at the index by the replacement."
  [s idx replacement]
  (str (subs s 0 idx) replacement (subs s (inc idx))))

(defn- abs [n] (max n (- n)))

(defn- column? [[x1 _] [x2 _]]
  (= x1 x2))

(defn- row? [[_ y1] [_ y2]]
  (= y1 y2))

(defn- diag? [[x1 y1] [x2 y2]]
  (= (abs (- y2 y1)) (abs (- x2 x1))))

(defn board-string [{:keys [w b]}]
  (if (every? nil? [w b])
    empty-board
    (-> empty-board
        (replace-at (* 2 (+ (second w) (* 8 (first w)))) "W")
        (replace-at (* 2 (+ (second b) (* 8 (first b)))) "B"))))

(defn can-attack [{:keys [w b]}]
  (or (row? w b)
      (diag? w b)
      (column? w b)))
