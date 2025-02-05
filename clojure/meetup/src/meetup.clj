(ns meetup
  (:import (java.time.temporal TemporalAdjusters)
           (java.time LocalDate DayOfWeek)))

(def ^:private weekday->enum
  {:sunday    DayOfWeek/SUNDAY
   :monday    DayOfWeek/MONDAY
   :tuesday   DayOfWeek/TUESDAY
   :wednesday DayOfWeek/WEDNESDAY
   :thursday  DayOfWeek/THURSDAY
   :friday    DayOfWeek/FRIDAY
   :saturday  DayOfWeek/SATURDAY})

(def ^:private descriptor->adjuster
  {:first   #(TemporalAdjusters/firstInMonth %)
   :second  #(TemporalAdjusters/dayOfWeekInMonth 2 %)
   :third   #(TemporalAdjusters/dayOfWeekInMonth 3 %)
   :fourth  #(TemporalAdjusters/dayOfWeekInMonth 4 %)
   :last    #(TemporalAdjusters/lastInMonth %)
   :teenth  #(TemporalAdjusters/next %)})

(defn meetup [month year weekday descriptor]
  (-> (LocalDate/parse (format "%04d-%02d-12" year month))
      (.with ((descriptor->adjuster descriptor) (weekday->enum weekday)))
      ((juxt #(.getYear %) #(.getMonthValue %) #(.getDayOfMonth %)))))
