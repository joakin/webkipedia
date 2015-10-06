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

(defn scroll-top! [] (.-scrollY js/window))

(defn set-scroll-top! []
  (let [state (or (.-state js/history) #js {})]
    (aset state "scroll-top" (scroll-top!))
    (.replaceState js/history state)))

(defn get-scroll-top []
  (when-let [state (.-state js/history)]
    (aget state "scroll-top")))

(defn handle-url-change! [e]
  #_(when-not (.-isNavigation e)
    (js/window.scrollTo 0 0))
  (secretary/dispatch! (.-token e)))

(defn init []
  (goog.events/listen
    history EventType/NAVIGATE #(handle-url-change! %))
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
    (do
      (change-route! :search q)
      (new-search! q))))

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
