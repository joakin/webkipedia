(ns webkipedia.router
  (:require [secretary.core :as secretary :refer-macros [defroute]]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [webkipedia.state.route :refer [set-route!]]
            [webkipedia.state.menu :refer [hide-menu!]]
            [webkipedia.state.page :as page])
  (:import goog.History))

(secretary/set-config! :prefix "#")

(defn init []
  (let [h (History.)]
    (goog.events/listen
      h EventType/NAVIGATE #(secretary/dispatch! (.-token %)))
    (doto h (.setEnabled true))))

;; Route definitions
(defn update-route! [route]
  (set-route! route)
  (hide-menu!))

; home
(defroute "/" []
  (update-route! :home)
  (println "home"))

; search
(defroute #"/search(/.+)?/" [q]
  (update-route! :search)
  (println (str "search" q)))

; page
(defroute "/wiki/:title" [title]
  (update-route! :page)
  (println (str "page " title))
  (page/set-title! title)
  (page/load-page!)
  )

; explore
(defroute #"/explore(/.*)?" [buster]
  (update-route! :explore)
  (println (str "explore" buster)))

