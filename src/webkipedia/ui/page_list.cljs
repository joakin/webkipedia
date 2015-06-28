(ns webkipedia.ui.page-list
  (:require [clojure.string :as s]
            [webkipedia.ui.thumbnail :as t]))

(defn page-list-item [{:keys [title thumbnail] :as item}]
  (let [escaped-title (s/replace title #" " "_")
        url (str "#/wiki/" escapedTitle)]
    [:a.PageListItem {:href url}
     [t/thumbnail thumbnail]
     [:span.PageListItem-title title]]))

(defn page-list []
  )
