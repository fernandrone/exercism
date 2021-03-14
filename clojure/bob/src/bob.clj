(ns bob
  (:require [clojure.string :as string]))

(defn is-shout? [s]
  (some->> s
           (filter #(Character/isLetter %))
           (not-empty)
           (every? #(Character/isUpperCase %))))

(defn is-question? [s]
  (string/ends-with? (string/trimr s) "?"))

(defn is-silence? [s]
  (string/blank? s))

(defn response-for [s]
  (condp apply [s]
    (every-pred is-question? is-shout?) "Calm down, I know what I'm doing!"
    is-question? "Sure."
    is-shout?    "Whoa, chill out!"
    is-silence?  "Fine. Be that way!"
    "Whatever."))
