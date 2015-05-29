(ns webkipedia.core
    (:require [reagent.core :as reagent]
              [webkipedia.state :refer [state]]
              [webkipedia.ui.webkipedia :refer [webkipedia]]))

(enable-console-print!)

(defn render []
  (reagent/render-component [webkipedia state]
                            (.-body js/document)))

(render)

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  (render)
)

