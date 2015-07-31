(ns webkipedia.actions.explore
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [webkipedia.dispatcher :refer [dispatch]]
            [webkipedia.api.random :refer [random]]
            [cljs.core.async :refer [<!]]
            ))

(defn load-random! []
  (dispatch :random/reset)
  (go ; Load random pages
    (let [result (<! (random))
          success (:success result)
          random-pages (:body result)]
      (dispatch :random/results random-pages))))
