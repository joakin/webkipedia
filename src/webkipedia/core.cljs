(ns webkipedia.core
    (:require [reagent.core :as reagent]
              [webkipedia.state.core]
              [webkipedia.router :as router]
              [webkipedia.ui.webkipedia :refer [webkipedia]]
              [webkipedia.db :as db]
              [webkipedia.scratch]))

(enable-console-print!)

(defn render! []
  (reagent/render-component [webkipedia]
                            (.-body js/document)))

(.initializeTouchEvents js/React true)
(db/init!)
(render!)
(router/init)

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  (render!)
)
