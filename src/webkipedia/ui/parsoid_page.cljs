(ns webkipedia.ui.parsoid-page)

(defn parsoid-page [content]
  [:div.ParsoidPage
   {:dangerouslySetInnerHTML {:__html content}}])
