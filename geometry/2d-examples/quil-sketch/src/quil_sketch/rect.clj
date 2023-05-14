(ns quil-sketch.rect
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [quil-sketch.xy :refer :all]))

(defn setup []
  (q/frame-rate 60)
  (q/color-mode :rgb)
  (q/stroke-weight 2)
  {:rect {:x 0 :y 0 :size 50}})

(defn draw [{:keys [rect] :as state}]
  (q/background 255)
  (q/stroke 0)
  (q/fill 200)
  (q/point 0 0)
  (q/rect (:x rect) (:y rect) (:size rect) (:size rect)))

(defn in-rect? [rect xy]
  (let [{:keys [x y]} xy
        {rx :x ry :y r-size :size} rect]
    (and (<= rx x (+ rx r-size))
         (<= ry y (+ ry r-size)))))

(defn mouse-dragged [state event]
  ;; (println "mouse-dragged" {:state state :event event} )
  (let [prev (get-xy event :p-x :p-y)
        cur (get-xy event)
        dxy (-xy cur prev)]
    (cond
      ;; move rect
      (and (= :left (:button event))
           (in-rect? (:rect state) prev))
      (update state :rect #(assoc-xy % (+xy % dxy)))
      ;; move plane
      (= :left (:button event))
      state ;; TODO
      ;; do nothing
      :else state)))

(q/defsketch drag-sketch
  :title "Sketch with moving rectangle"
  :setup setup
  :update identity
  :draw draw
  :mouse-clicked mouse-clicked
  :mouse-dragged mouse-dragged
  :middleware [m/fun-mode])

