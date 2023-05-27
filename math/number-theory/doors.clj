;; https://brilliant.org/courses/basic-number-theory/factorization/100-doors/1/
(ns doors
  (:require [clojure.string :as str]))

(defn init-doors [n]
  (repeat n false))
(comment
  (init-doors 2) ;; => (false false)
  )

(defn div? [m n] (zero? (rem m n)))
(comment
  (div? 2 1) ;; => true
  (div? 3 2) ;; => false
  )

(defn flip-every [doors k]
  (map-indexed
   (fn [i open] (if (div? (inc i) k)
                  (not open) open))
   doors))

(comment
  (flip-every (init-doors 5) 1) ;; => (true true true true true)
  (flip-every (init-doors 5) 2) ;; => (false true false true false)
  )

(defn str-doors [doors]
  (->> doors
       (map #(if % "_" "#"))
       (apply str)))

(comment
  (str-doors (flip-every (init-doors 5) 2)) ;; => "#_#_#"
  )

(defn flips [doors n]
  (loop [k 1
         res [doors]]
    (if (> k n) res
        (recur (inc k)
               (conj res (flip-every (last res) k))))))

(comment
  (->> (flips (init-doors 5) 5)
       (map str-doors))
  ;; => ("#####" "_____" "_#_#_" "_###_" "_##__" "_##_#")
  )

(defn count-open-doors [doors]
  (->> doors
       (filter identity)
       (count)))

(defn open-doors [doors]
  (->> doors
       (map-indexed (fn [i d] [(inc i) d]))
       (filter second)
       (map first)))

(comment
  (count-open-doors (flip-every (init-doors 5) 2)) ;; => 2
  (open-doors (flip-every (init-doors 5) 2)) ;; => (2 4)
  )

(defn print-doors-seq [seq]
  (let [lines (map str-doors seq)]
    (print (str "\ndoors:\n"
                (str/join "\n" lines)
                "\ncount of open doors: "
                (count-open-doors (last seq))
                "\nopen doors: "
                (pr-str (open-doors (last seq)))
                "\n"))))

(comment
  (->> (flips (init-doors 5) 5)
       (print-doors-seq))
  ;; doors:
  ;; #####
  ;; _____
  ;; _#_#_
  ;; _###_
  ;; _##__
  ;; _##_#
  ;; count of open doors: 2
  ;; open doors: (1 4)

  (->> (flips (init-doors 100) 100)
       (print-doors-seq))
  ;; ...
  ;; _##_####_######_########_##########_############_##############_################_##################_
  ;; count of open doors: 10
  ;; open doors: (1 4 9 16 25 36 49 64 81 100)

  )

