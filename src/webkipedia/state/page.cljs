(ns webkipedia.state.page
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as reagent :refer [atom]]
            [cljs.core.async :refer [<!]]
            [webkipedia.api.article :as article]
            [webkipedia.api.related :refer [related-pages]]
            ))

(def page
  (atom {:title nil
         :content nil}))

(defn set-title! [title]
  (swap! page assoc :title title))

(defn set-content! [content]
  (swap! page assoc :content content))

(defn reset-content! []
  (set-content! nil))

(defn set-related! [related]
  (swap! page assoc :related related))

(defn reset-related! []
  (set-related! nil))

(defn content-loaded?
  "Returns true if the current title and the title of the loaded content is the same"
  [p]
  (let [content (:content p)
        title (:title p)]
    (and (not (nil? title))
         (= title (:title content)))))

(defn load-page! []
  (let [p @page
        content (:content p)
        title (:title p)
        not-loaded? (not (content-loaded? p))]
    (when not-loaded?
      ; Clear the content while we request it
      (reset-content!)
      (reset-related!)
      ; Request article content
      (go
        (let [result (<! (article/summary title))
              success (:success result)
              content (:body result)]
          (println content)
          ; If the content is relevant for the current page, swap it
          (if (= (:title content) (:title @page))
            (set-content! content))))
      ; Request related pages
      (go
        (let [result (<! (related-pages title))
              success (:success result)
              related (:body result)]
          (println related)
          ; If the result is relevant for the current page, swap it
          (if (= (:title related) (:title @page))
            (set-related! related))))
      )))
