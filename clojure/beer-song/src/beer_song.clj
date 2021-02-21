(ns beer-song)
(require '[clojure.string :as string])

(def verse-0
  (str "No more bottles of beer on the wall, no more bottles of beer.\n"
       "Go to the store and buy some more, 99 bottles of beer on the wall.\n"))

(def verse-2
  (str "2 bottles of beer on the wall, 2 bottles of beer.\n"
       "Take one down and pass it around, 1 bottle of beer on the wall.\n"))

(def verse-1
  (str "1 bottle of beer on the wall, 1 bottle of beer.\n"
       "Take it down and pass it around, no more bottles of beer on the wall.\n"))

(defn verse-n [n]
  (str n " bottles of beer on the wall, " n " bottles of beer.\n"
       "Take one down and pass it around, " (dec n) " bottles of beer on the wall.\n"))

(defn verse
  "Returns the nth verse of the song."
  [n]
  (cond
    (= n 0) verse-0
    (= n 1) verse-1
    (= n 2) verse-2
    :else (verse-n n)))

(defn sing
  "Given a start and an optional end, returns all verses in this interval. If
  end is not given, the whole song from start is sung."
  ([start] (sing start 0))
  ([start end]
   (let [dec-list-of-verses (range start (dec end) -1)]
     (string/join "\n" (map verse dec-list-of-verses)))))
