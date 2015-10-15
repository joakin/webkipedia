(ns webkipedia.actions.page
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [webkipedia.dispatcher :refer [dispatch]]
            [cljs.core.async :refer [<!]]
            [webkipedia.api.parsoid-article :as parsoid-article]
            [webkipedia.api.related :refer [related-pages]]
            [webkipedia.state.page :refer [page]]
            ))

(defn- content-loaded?
  "Returns true if the current title and the title of the loaded content is the same"
  [page]
  (let [content (:content page)
        title (:title page)]
    (and (not (nil? title))
         (= title (:title content)))))

(defn load-related! [title]
  (go
    (let [result (<! (related-pages title))
          success (:success result)
          related (:body result)
          results-are-relevant? (= (:title related) (:title @page))]
      ; If the result is relevant for the current page, swap it
      (if (and success results-are-relevant?)
        (dispatch :page/related related)))))

(defn load-parsoid-content! [title]
  (go
    (let [result (<! (parsoid-article/article title))
          success (:success result)
          content (:body result)
          content-is-relevant? (= (:title content) (:title @page))]
      ; If the content is relevant for the current page, swap it
      (if (and success content-is-relevant?)
        (dispatch :page/content content)))))


(defn change-page! [title]
  (dispatch :page/change title)
  (when-not (content-loaded? @page)
    (load-parsoid-content! title)
    (load-related! title)
    ))

