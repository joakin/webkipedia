(ns webkipedia.state.history
  (:require [reagent.core :as reagent :refer [atom]]
            [webkipedia.db :refer [db-get db-set]]))

(def hist-key :article-history)

(let [db-history (db-get hist-key)]
  (defonce history
    (atom {:items (if db-history (get-in db-history [:value :items]) '())})))

(defn add-entry [history title]
  (if (not= (:title (first (:items history))) title)
    (update
      history :items conj
      {:date (.now js/Date) :title title})
    history))

(defn dispatch [state action payload]
  (case action
    :page/load
    (let [new-hist (add-entry state payload)]
      (db-set hist-key new-hist)
      new-hist)
    state))
