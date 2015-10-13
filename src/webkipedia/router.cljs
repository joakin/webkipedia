(ns webkipedia.router
  (:require [secretary.core :as secretary :refer-macros [defroute]]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [webkipedia.dispatcher :refer [dispatch]]
            [webkipedia.actions.search :refer [reset-search! new-search!]]
            [webkipedia.actions.menu :refer [hide-menu!]]
            [webkipedia.actions.explore :refer [load-random!]]
            [webkipedia.actions.page :refer [change-page!]]
            [webkipedia.actions.route :refer [change-route!]]
            [webkipedia.state.menu :as menu]
            [webkipedia.state.route :as route]
            )
  (:import goog.History))

(secretary/set-config! :prefix "#")

(def history (History.))

(defn init []
  (goog.events/listen
    history EventType/NAVIGATE #(secretary/dispatch! (.-token %)))
  (.setEnabled history true))

(defn navigate! [url]
  (.setToken history url))

(defn replace! [url]
  (.replaceToken history url))

;; Route definitions

; home
(defroute "/" []
  (change-route! :home)
  (reset-search!))

; search
(defroute #"/search(/(.*))?/" [_ q]
  (if (= q "")
    (replace! "/") ; If there's no query, show home
    (let [eq (js/decodeURIComponent q)]
      (change-route! :search eq)
      (new-search! eq))))

; page
(defroute #"/wiki/(.*)" [title]
  (change-route! :page title)
  (change-page! title))

; explore
(defroute #"/explore(/.*)?" [buster]
  (change-route! :explore)
  (load-random!))

; history
(defroute #"/history" []
  (change-route! :history))

; about
(defroute #"/about" []
  (change-route! :about))
