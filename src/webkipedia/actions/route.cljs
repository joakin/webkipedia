(ns webkipedia.actions.route
  (:require [webkipedia.dispatcher :refer [dispatch]]
            [webkipedia.state.route :refer [current]]
            ))

(defn change-route! [route & args]
  (when (not= route (current))
    (dispatch :route/new [route args])))

