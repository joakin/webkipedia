(ns webkipedia.state.history
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as reagent :refer [atom]]
            [cljs.core.async :refer [<!]]
            [webkipedia.db :refer [set-item get-item parse stringify]]))

(def hist-key "article-history")

(defonce history (atom {:items '()}))

(defonce init-history
  (do
    ;; Fetch the default history
    (go
      (let [[status value] (<! (get-item hist-key))]
        (if (= status :err)
          (do
            (println "Error saving history")
            (println value))
          (when value
            (swap! history assoc :items (parse value))))))
    ;; Watch atom and save th DB on change
    (add-watch
      history :save-to-db
      (fn [key a old-state new-state]
        (go
          (let [items (:items new-state)
                [status value] (<! (set-item hist-key (stringify items)))]
            (when (= status :err)
              (println "Error saving history")
              (println value))))))
    ))

(defn add-entry [history title]
  (if (not= (:title (first (:items history))) title)
    (update
      history :items conj
      {:date (.now js/Date) :title title})
    history))

(defn dispatch [state action payload]
  (case action
    :page/change (add-entry state payload)
    state))
