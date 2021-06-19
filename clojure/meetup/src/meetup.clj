(ns meetup
  (:import (java.time.temporal TemporalAdjusters)
           (java.time LocalDate DayOfWeek)))

(def weekday->enum
  {:sunday    DayOfWeek/SUNDAY
   :monday    DayOfWeek/MONDAY
   :tuesday   DayOfWeek/TUESDAY
   :wednesday DayOfWeek/WEDNESDAY
   :thursday  DayOfWeek/THURSDAY
   :friday    DayOfWeek/FRIDAY
   :saturday  DayOfWeek/SATURDAY})

(defn meetup [month year weekday descriptor]
  (let [wd (weekday->enum weekday)]
    (cond-> (LocalDate/parse (format "%04d-%02d-12" year month))
      (= descriptor :first)  (.with (TemporalAdjusters/firstInMonth wd))
      (= descriptor :second) (.with (TemporalAdjusters/dayOfWeekInMonth 2 wd))
      (= descriptor :third)  (.with (TemporalAdjusters/dayOfWeekInMonth 3 wd))
      (= descriptor :fourth) (.with (TemporalAdjusters/dayOfWeekInMonth 4 wd))
      (= descriptor :last)   (.with (TemporalAdjusters/lastInMonth wd))
      (= descriptor :teenth) (.with (TemporalAdjusters/next wd))
      :always ((juxt #(.getYear %) #(.getMonthValue %) #(.getDayOfMonth %))))))
