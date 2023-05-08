(ns quil-sketch.rect
  (:require [quil.core :as q]))

(def state (atom {:x 0 :y 0}))

(defn setup []
  (q/frame-rate 60)
  (q/color-mode :rgb)
  (q/stroke-weight 2))

(defn draw []
  (q/background 255)
  (q/stroke 0)
  (q/fill 200)
  (q/rect (:x @state) (:y @state) 50 50))

(defn mouse-dragged []
  (swap! state assoc :x q/mouse-x :y q/mouse-y))

(q/defsketch my-sketch
  :title "Sketch with moving rectangle"
  :setup setup
  :draw draw
  :mouse-dragged mouse-dragged)


