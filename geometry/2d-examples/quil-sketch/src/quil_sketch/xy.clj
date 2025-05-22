(ns quil-sketch.xy)

(defn xy [x y] {:x x :y y})

(defn xy->vec [{:keys [x y]}] [x y])

(defn +xy [{x1 :x y1 :y}
           {x2 :x y2 :y}]
  (xy (+ x1 x2)
      (+ y1 y2)))

(defn -xy [{x1 :x y1 :y}
           {x2 :x y2 :y}]
  (xy (- x1 x2)
      (- y1 y2)))

(defn *xy [{:keys [x y]} k]
  (xy (* x k) (* y k)))

(defn div-xy [{:keys [x y]} & ks]
  (xy (apply / x ks)
      (apply / y ks)))

(defn norm-xy [{:keys [x y]}]
  (Math/sqrt (+ (* x x) (* y y))))

(defn distance-xy [a b]
  (norm-xy (-xy a b)))

(defn get-xy
  ([m] (get-xy m :x :y))
  ([m kx ky] (xy (kx m) (ky m))))

(defn assoc-xy [m {:keys [x y]}]
  (assoc m :x x :y y))
