(ns webkipedia.ui.main-page
  (:require [webkipedia.ui.search-box :refer [search-box]]
            [webkipedia.ui.loading :refer [loading]]
            [webkipedia.state.search :refer [search]]))

(defn main-page []
  (let [{:keys [query results]} @search
        searching-class (if query "is-searching" "")]
    [:div.MainPage
     {:class searching-class}
     [:h1.MainPage-header "Wikipedia"]
     [search-box {:query query :on-change println}]
     (if loading [loading] nil)]))
