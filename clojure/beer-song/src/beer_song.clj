(ns beer-song)

(def verse-0
  (str "No more bottles of beer on the wall, no more bottles of beer.\n"
       "Go to the store and buy some more, 99 bottles of beer on the wall.\n"))

(def verse-2
  (str "2 bottles of beer on the wall, 2 bottles of beer.\n"
       "Take one down and pass it around, 1 bottle of beer on the wall.\n"))

(def verse-1
  (str "1 bottle of beer on the wall, 1 bottle of beer.\n"
       "Take it down and pass it around, no more bottles of beer on the wall.\n"))

(defn verse-n [num]
  (str num " bottles of beer on the wall, " num " bottles of beer.\n"
       "Take one down and pass it around, " (- num 1) " bottles of beer on the wall.\n"))

(defn verse
  "Returns the nth verse of the song."
  [num]
  (if (< num 3)
    ;; if num < 3 then return 'verse-0' for num = 0, 'verse-1' for num = 1, etc.
    ;; verses 0, 1 and 2 are special cases and this implementation avoids nested ifs
    (get {0 verse-0, 1 verse-1, 2 verse-2} num)
    ;; otherwise call generic "verse-n" function
    (verse-n num)))

(defn sing
  "Given a start and an optional end, returns all verses in this interval. If
  end is not given, the whole song from start is sung."
  ([start] (sing start 0))
  ([start end]
   (if (> start end)
     ;; joins current verse with the next verse w/ a newline inbetween using recursion
     (str (verse start) "\n" (sing (dec start) end))
     ;; returns the 'end' verse at the stop condition (start == end)
     (verse end))))
