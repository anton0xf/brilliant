(ns swing-drag.core
  (:gen-class)
  (:require [seesaw.core :refer :all]
            [seesaw.graphics :as g2]
            [swing-drag.util :refer :all]))

(native!)

(defn paint-scene [c g]
  (let [w (.getWidth c)
        h (.getHeight c)
        line-style (g2/style :foreground "mediumpurple"
                             :stroke (g2/stroke :width 0.005))]
    (g2/push
     g (doto g
         (g2/scale w h)
         (draw-styled
          line-style
          (g2/ellipse 0.1 0.1 0.8 0.8)
          (g2/rect 0.3 0.3 0.4 0.4)
          (g2/ellipse 0.3 0.3 0.4 0.4)
          (g2/line 0.3 0.3 0.7 0.7))))))

(def scene (canvas :paint #(paint-scene %1 %2)))
(def status-panel (label "Status panel"))

(def main-pane (border-panel :center scene
                             :south status-panel))

(def main-frame
  (frame :title "Dragging square"
         :on-close :dispose ;; :exit
         :size [300 :by 100]
         :content main-pane))

(comment
  (config! main-frame :content main-pane)
  (first (config main-pane :south))
  (config! status-panel :text "Status!")
  (config! scene :paint paint-scene)
  (config! scene :background java.awt.Color/LIGHT_GRAY)
  (.repaint scene)
  )

(defn -main [& args]
  (invoke-later
   (-> main-frame
       ;; pack!
       ;; maximize-frame!
       show!)))
