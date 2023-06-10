(ns swing-drag.util
  (:require [seesaw.graphics :as g2]))

(defn maximize-frame! [f]
  (.setExtendedState f java.awt.Frame/MAXIMIZED_BOTH)
  f)

(defn draw-styled [g style & shapes]
  (doseq [shape shapes]
    (g2/draw g shape style)))
