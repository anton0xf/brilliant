(ns swing-drag.core
  (:gen-class)
  (:require [seesaw.core :refer :all]
            [swing-drag.util :refer :all]))

(native!)

(def scene (canvas))

(def main-pane (border-panel :center scene
                             :south "Status panel"))

(def main-frame
  (frame :title "Dragging square"
         :on-close :exit
         :size [300 :by 100]
         :content main-pane))

(comment
  (config! main-frame :content main-pane)
  (config! main-pane :center scene)
  (.repaint main-pane)
  )

(defn -main [& args]
  (invoke-later
   (-> main-frame
       ;; pack!
       ;; maximize-frame!
       show!)))
