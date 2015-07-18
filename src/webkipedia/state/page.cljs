(ns webkipedia.state.page
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as reagent :refer [atom]]
            [cljs.core.async :refer [<!]]
            [webkipedia.api.article :as article]
            [webkipedia.api.mobileview-article :as mobileview-article]
            [webkipedia.api.related :refer [related-pages]]
            [webkipedia.dispatcher :refer [register]]
            ))

(defonce page
  (atom {:title nil
         :content nil}))

(defn set-title [page title]
  (assoc page :title title))

(defn set-content [state content]
  (assoc state :content content))

(defn set-related [state related]
  (assoc state :related related))

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
  [page]
  (let [content (:content page)
        title (:title page)]
    (and (not (nil? title))
         (= title (:title content)))))

(defn load-page! [title current-page]
  (let [content (:content current-page)
        not-loaded? (not (content-loaded? current-page))]
    (when not-loaded?
      ; Request article content
      (go
        (let [result (<!
                       #_(article/summary title)
                       (mobileview-article/summary title))
              success (:success result)
              content (:body result)]
          ; If the content is relevant for the current page, swap it
          (if (= (:title content) (:title @page))
            (set-content! content))))
      ; Request related pages
      (go
        (let [result (<! (related-pages title))
              success (:success result)
              related (:body result)]
          ; (println related)
          ; If the result is relevant for the current page, swap it
          (if (= (:title related) (:title @page))
            (set-related! related))))
      )))

(defn load-page-content! [{:keys [title] :as state}]
  (go
    (let [result (<! (mobileview-article/content title))
          success (:success result)
          content (:body result)]
      ; If the content is relevant for the current page, swap it
      (if (= (:title content) (:title @page))
        (let [summary (first (get-in @page [:content :sections]))
              sections (assoc (:sections content) 0 summary)
              merged-content (assoc (:content @page) :sections sections)]
          (set-content! merged-content))))))

(defn dispatch [state action payload]
  (case action
    :page/load
    (let [title payload
          new-state ; Clear and return the content while we request it
          (-> state
              (set-content nil)
              (set-related nil)
              (set-title title))]
      (load-page! title new-state)
      new-state)
    :page/load-content
    (let []
      (load-page-content! state)
      state)
    state))

(register :page dispatch page)
