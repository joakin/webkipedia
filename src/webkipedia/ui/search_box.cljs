(ns webkipedia.ui.search-box)

(defn search-box [{:keys [query on-change]}]
  [:div.SearchBox
   [:form.SearchBox-form
    [:input.SearchBox-input
     {:type "text"
      :placeholder "Search"
      :value query
      :on-change #(on-change (.. % -target -value))}]]])
