(ns webkipedia.ui.main-page
  (:require [webkipedia.ui.search-box :refer [search-box]]
            [webkipedia.ui.loading :refer [loading]]
            [webkipedia.ui.page-list :refer [page-list]]
            [webkipedia.state.search :refer [search]]
            [webkipedia.router :as router]))

(defn main-page []
  (let [{:keys [query results]} @search
        searching-class (if query "is-searching" "")
        results-list (:list results)
        loading? (not= query (:query results))]
    [:div.MainPage
     {:class searching-class}
     [:h1.MainPage-header "Wikipedia"]
     [search-box {:query query
                  :on-change #(router/replace!
                                (str "/search/"
                                     (js/encodeURIComponent %)
                                     "/"))}]
     (if loading? [loading] nil)
     [page-list results-list]]))
