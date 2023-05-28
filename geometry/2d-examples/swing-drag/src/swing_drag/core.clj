(ns swing-drag.core
  (:gen-class)
  (:require [seesaw.core :refer :all]))

(def f (frame :title "Get to know Seesaw"))
(def lbl (label "I'm another label"))
(defn display [content]
  (config! f :content content)
  content)
(def b (button :text "Click Me"))

(defn -main
  [& args]
  ;; example from https://gist.github.com/daveray/1441520
  (native!)
  (-> f pack! show!)
  (config f :title)
  (config! f :title "No RLY, get to know Seesaw!")
  (config! f :content "This is some content")
  (config! f :content lbl)
  (pack! f)
  (display lbl)
  (config! lbl :background :pink :foreground "#00f")
  (config! lbl :font "ARIAL-BOLD-21")
  (alert "I'm an alert")
  (input "What's your favorite color?")
  ;; => "blue"
  (display b)
  (listen b :action (fn [e] (alert e "Thanks!")))
  ;; (*1) ;; unregister action
  )
