(ns webkipedia.core
    (:require [reagent.core :as reagent]
              [webkipedia.state :refer [state]]
              [webkipedia.router :as router]
              [webkipedia.ui.webkipedia :refer [webkipedia]]))

(enable-console-print!)

(defn render []
  (reagent/render-component [webkipedia state]
                            (.-body js/document)))

(render)
(router/init)

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  (render)
)
