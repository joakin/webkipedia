(ns webkipedia.core
    (:require [reagent.core :as reagent]
              [webkipedia.state.core :as state]
              [webkipedia.router :as router]
              [webkipedia.ui.webkipedia :refer [webkipedia]]
              [webkipedia.db.core :as db]
              [webkipedia.scratch]))

(enable-console-print!)


(defn render! []
  (if-let [node (.getElementById js/document "app")]
    (reagent/render-component [webkipedia] node)))

(.initializeTouchEvents js/React true)
(db/init!)
(state/init!)
(render!)
(router/init)

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  (render!)
)
