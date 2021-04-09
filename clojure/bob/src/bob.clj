(ns bob
  (:require [clojure.string :as string]))

(defn shout? [s]
  (some->> s
           (filter #(Character/isLetter %))
           (not-empty)
           (every? #(Character/isUpperCase %))))

(defn question? [s]
  (string/ends-with? (string/trimr s) "?"))

(def silence? string/blank?)

(defn response-for [s]
  (cond
    ((every-pred question? shout?) s) "Calm down, I know what I'm doing!"
    (question? s) "Sure."
    (shout? s)    "Whoa, chill out!"
    (silence? s)  "Fine. Be that way!"
    :else "Whatever."))
