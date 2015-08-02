(ns webkipedia.ui.page-list
  (:require [clojure.string :as s]
            [webkipedia.ui.thumbnail :as t]
            [webkipedia.ui.css-transition-group :refer [css-transition-group]]))

(defn length-class [text]
  (let [n (count text)
        max-num 4]
    (.min js/Math (.floor js/Math (/ n 10)) max-num)))

(defn page-list-item [{:keys [title thumbnail] :as item}]
  (let [escaped-title (s/replace title #" " "_")
        url (str "#/wiki/" escaped-title)]
    [:a.PageListItem {:href url}
     [t/thumbnail thumbnail]
     [:span.PageListItem-title
      {:class (str "is-length-" (length-class escaped-title))}
      title]]))

(defn page-list [items]
  [:div.PageList
   [css-transition-group {:transition-name "PageList-item-wrapper"}
    (for [item items]
      [:div.PageList-item-wrapper
       {:key (str (:title item) (:date item))}
       [page-list-item item]])]])
