(ns quil-sketch.xy)

(defn xy [x y] {:x x :y y})

(defn +xy [{x1 :x y1 :y}
           {x2 :x y2 :y}]
  (xy (+ x1 x2)
      (+ y1 y2)))

(defn -xy [{x1 :x y1 :y}
           {x2 :x y2 :y}]
  (xy (- x1 x2)
      (- y1 y2)))

(defn get-xy [m] (xy (:x m) (:y m)))

(defn assoc-xy [m {:keys [x y]}]
  (assoc m :x x :y y))
