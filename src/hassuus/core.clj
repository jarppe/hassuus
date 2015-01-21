(ns hassuus.core
  (:require [clojure.java.io :as io]
            [clojure.string :as s])
  (:gen-class))

(defn length-score [length]
  (* length (long (Math/pow 2 length))))

(defn funnyness [word]
  (->> word
       (re-seq #"[aeiouyäö]+")
       (reduce (fn [acc v]
                 (+ acc (length-score (count v))))
               0)))

(defn rate [[max-score words :as prev] word]
  (let [score (funnyness word)]
    (cond
      (> score max-score) [score #{word}]
      (= score max-score) [score (conj words word)]
      :else prev)))

(defn find-funniest-words [source]
  (->> source
       (io/input-stream)
       (io/reader)
       (line-seq)
       (mapcat (comp (partial re-seq #"[\wåäö]+") s/lower-case))
       (reduce rate [0 #{}])))

(defn -main [& args]
  (let [[score words] (find-funniest-words "http://wunderdog.fi/koodaus-hassuimmat-sanat/alastalon_salissa.txt")]
    (printf "Found %d words with funnyness score %d:\n" (count words) score)
    (doseq [word words]
      (println "  " word))))

; Prints:
; Found 6 words with funnyness score 72:
;    puuaineen
;    seremoniioilla
;    liioittelematta
;    leeaakaan
;    niiaamaan
;    seremoniioissa
