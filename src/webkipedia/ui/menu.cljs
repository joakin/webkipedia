(ns webkipedia.ui.menu)

(defn menu-item [[k {:keys [attrs label]}]]
  [:li.MenuItem {:key k}
   [:a attrs label]])

(defn menu [items]
  [:div.Menu
   [:ul.Menu-list
    (map menu-item items)]])

