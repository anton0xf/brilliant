(ns quil-sketch.navigation
  (:require [quil.core :as q :include-macros true]))

(def ^:private ^String missing-navigation-key-error
  (str "state map is missing :navigation key. "
       "Did you accidentally removed it from the state in "
       ":update or any other handler?"))

(defn- assert-state-has-navigation
  "Asserts that `state` map contains `:navigation` object."
  [state]
  (when-not (:navigation state)
    (throw #?(:clj (RuntimeException. missing-navigation-key-error)
              :cljs (js/Error. missing-navigation-key-error)))))

(defn- default-position
  "Default position configuration: zoom is neutral
  and central point is `width/2, height/2`."
  []
  {:position [(/ (q/width) 2.0)
              (/ (q/height) 2.0)]
   :zoom 1})

(defn- setup-nav
  "Custom 'setup' function which creates initial position
  configuration and puts it to the state map."
  [user-setup user-navigation-settings]
  (let [initial-state (-> user-navigation-settings
                          (select-keys [:position :zoom :debug])
                          (->> (merge (default-position))))]
    (update-in (user-setup) [:navigation]
               #(merge initial-state %))))

(defn- mouse-dragged
  "Changes center of the sketch depending on the last mouse move.
  Takes zoom into account as well."
  [state event]
  (assert-state-has-navigation state)
  (if (not= :left (:button event))
    state
    ;; TODO refactor using xy
    (let [dx (- (:p-x event) (:x event))
          dy (- (:p-y event) (:y event))
          zoom (-> state :navigation :zoom)]
      (-> state
          (update-in [:navigation :position 0] + (/ dx zoom))
          (update-in [:navigation :position 1] + (/ dy zoom))))))

(defn- mouse-wheel
  "Changes zoom settings based on scroll."
  [state event]
  (assert-state-has-navigation state)
  (update-in state [:navigation :zoom] * (+ 1 (* -0.1 event))))

(defn- draw-point [x y]
  (q/stroke-weight 5)
  (q/point x y)
  (q/text (format "(%d, %d)" x y) (+ x 5) (+ y 15)))

(defn- draw-oxy [color]
  (q/with-stroke color
    (q/stroke-weight 1)
    (let [w (q/width)
          h (q/height)]
      (q/line [(- w) 0] [w 0])
      (q/line [0 (- h)] [0 h]))
    (doseq [t (->> (range 10) (map #(* (- % 5) 50)))]
      (draw-point t t)
      (draw-point t (- t)))))

(defn- draw
  "Calls user draw function with all necessary transformations
  (position and zoom) applied."
  [user-draw state]
  (assert-state-has-navigation state)
  (let [nav (:navigation state)
        zoom (:zoom nav)
        pos (:position nav)
        debug (:debug nav)]
    (q/push-matrix)
    (q/scale zoom)
    (q/with-translation [(- (/ (q/width) 2 zoom) (first pos))
                         (- (/ (q/height) 2 zoom) (second pos))]
      (user-draw state)
      (when debug (draw-oxy [100 150 200])))
    (q/pop-matrix)))

(defn navigation
  "Enables navigation over sketch. Dragging mouse will move center of the
  sketch and mouse wheel controls zoom."
  [options]
  (let [; navigation related user settings
        user-navigation-settings (:navigation options)
        ;; user-provided handlers which will be overridden by navigation
        user-draw (:draw options (fn [state]))
        ;; user's mouse-dragged fn accept arguments:
        ;; state, event, navigation mouse-dragged function
        user-mouse-dragged (:mouse-dragged options (fn [state _ _] state))
        user-mouse-wheel (:mouse-wheel options (fn [state _] state))
        setup (:setup options (fn [] {}))]
    (assoc options
           :setup (partial setup-nav setup user-navigation-settings)

           :draw (fn [state] (draw user-draw state))

           :mouse-dragged (fn [state event]
                            (user-mouse-dragged state event mouse-dragged))

           :mouse-wheel (fn [state event]
                          (user-mouse-wheel (mouse-wheel state event) event)))))
