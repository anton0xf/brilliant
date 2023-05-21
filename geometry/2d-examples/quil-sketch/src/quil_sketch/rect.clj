(ns quil-sketch.rect
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [quil-sketch.xy :refer :all]
            [quil-sketch.navigation :refer [navigation]]))

(defn setup []
  (q/frame-rate 15)
  (q/color-mode :rgb)
  {:dot {:x 0 :y 0}
   :rect {:x 0 :y 0 :size 50}})

(defn draw [{:keys [dot rect] :as state}]
  (q/background 255)
  ;; dot
  (q/stroke-weight (if (:active dot) 15 10))
  (q/stroke 255 50 50)
  (q/fill 255 100 100)
  (q/point (:x dot) (:y dot))
  ;; rect
  (q/stroke-weight (if (:active rect) 4 2))
  (q/stroke 0)
  (q/fill 200)
  (q/rect (:x rect) (:y rect) (:size rect) (:size rect)))

(defn near-dot? [dot xy]
  (< (distance-xy dot xy) 5))

(defn in-rect? [rect xy]
  (let [{:keys [x y]} xy
        {rx :x ry :y r-size :size} rect]
    (and (<= rx x (+ rx r-size))
         (<= ry y (+ ry r-size)))))

(defn mouse-moved [state event]
  (println "mouse-moved" {:state state :event event})
  (let [xy (get-xy event)]
    (-> state
        (assoc-in [:dot :active] (near-dot? (:dot state) xy))
        (assoc-in [:rect :active] (in-rect? (:rect state) xy)))))

(defn mouse-dragged [state event navigation-mouse-dragged]
  (println "mouse-dragged" {:state state :event event} )
  (let [prev (get-xy event :p-x :p-y)
        cur (get-xy event)
        dxy (-xy cur prev)]
    (cond
      ;; move rect
      (and (= :left (:button event))
           (in-rect? (:rect state) prev))
      (update state :rect #(assoc-xy % (+xy % dxy)))
      ;; move plane
      (= :left (:button event)) (navigation-mouse-dragged)
      ;; do nothing
      :else state)))

(q/defsketch drag-sketch
  :title "Sketch with moving rectangle"
  :setup setup
  :update identity
  :draw draw
  :mouse-moved mouse-moved
  :mouse-dragged mouse-dragged
  :navigation {:debug true}
  :middleware [m/fun-mode navigation])
