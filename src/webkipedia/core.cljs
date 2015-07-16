(ns webkipedia.core
    (:require [reagent.core :as reagent]
              [webkipedia.router :as router]
              [webkipedia.ui.webkipedia :refer [webkipedia]]
              [webkipedia.db :as db]
              [webkipedia.state.core]
              [webkipedia.scratch]))

(enable-console-print!)

(defn render! []
  (reagent/render-component [webkipedia]
                            (.-body js/document)))

(db/init!)
(render!)
(router/init)

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  (render!)
)
