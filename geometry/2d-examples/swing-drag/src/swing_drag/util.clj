(ns swing-drag.util)

(defn maximize-frame! [f]
  (.setExtendedState f java.awt.Frame/MAXIMIZED_BOTH)
  f)

