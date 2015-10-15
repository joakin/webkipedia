(ns webkipedia.ui.parsoid-page)

(defn parsoid-page [content]
  (let [body (:content content)
        html (.-innerHTML body)]
    [:div.ParsoidPage
     [:div.ParsoidPage-content
      {:dangerouslySetInnerHTML
       {:__html html}
       :on-click
       #(let [target (.-target %)
              node-name (.-nodeName target)
              rel (.-rel target)]
          (.preventDefault %)
          (cond
            ; Wiki link
            (and (= node-name "A") (= rel "mw:WikiLink"))
            (.log js/console (.-href target))

            ; External link
            (and (= node-name "A") (= rel "mw:ExtLink"))
            (.log js/console "External: " (.-href target))
            ))
       }]]))
