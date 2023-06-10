(ns swing-drag.core
  (:gen-class)
  (:require [seesaw.core :refer :all]
            [seesaw.graphics :as g2]
            [swing-drag.util :refer :all]))

(native!)

(defn paint-scene [c g]
  (let [w (.getWidth c)
        h (.getHeight c)
        line-style (g2/style :foreground java.awt.Color/RED
                             :stroke (g2/stroke :width 0.005))]
    (g2/push
     g (doto g
         (g2/scale w h)
         (draw-styled
          line-style
          (g2/line 0 0 1 1)
          (g2/line 1 0 0 1))))))

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
  (.repaint scene)
  )

(defn -main [& args]
  (invoke-later
   (-> main-frame
       ;; pack!
       ;; maximize-frame!
       show!)))
