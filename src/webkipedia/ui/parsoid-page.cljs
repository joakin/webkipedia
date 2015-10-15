(ns webkipedia.ui.parsoid-page)

(defn parsoid-page [content]
  (let [body (:content content)
        html (.-innerHTML body)]
    [:div.ParsoidPage
     [:div.ParsoidPage-content
      {:dangerouslySetInnerHTML
       {:__html html}}]
     ]))
