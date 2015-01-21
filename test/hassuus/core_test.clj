(ns hassuus.core-test
  (:require [midje.sweet :refer :all]
            [hassuus.core :refer :all]))

(tabular
  (fact funnyness
    (funnyness ?word) => ?expected)
  ?word               ?expected
  ; Examples from http://wunderdog.fi/koodaus-hassuimmat-sanat/
  "koira"             10
  "hääyöaie"          896
  ; Corner cases
  ""                  0
  "x"                 0
  ; Pretty funny words
  "häävejä"           (+ (length-score 2) (length-score 1) (length-score 1))
  "parrasirvetkin"    (+ (length-score 1) (length-score 1) (length-score 1) (length-score 1) (length-score 1))
  "oolantereille"     (+ (length-score 2) (length-score 1) (length-score 1) (length-score 2) (length-score 1))
  ; Super funny words
  "liioittelematta"   72
  "leeaakaan"         72)

(tabular
  (fact
    (rate ?prev ?word) => ?expected)
  ?prev             ?word       ?expected
  [0 #{}]           "koira"     [10 #{"koira"}]
  [10 #{"foo"}]     "koira"     [10 #{"foo" "koira"}]
  [100 #{"foo"}]    "koira"     [100 #{"foo"}])
