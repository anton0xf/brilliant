(ns swing-drag.pain-examples
  (:require [seesaw.graphics :as g2]
            [swing-drag.util :refer :all]))

(defn paint-red-cross [c g]
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
