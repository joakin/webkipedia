(ns webkipedia.actions.menu
  (:require [webkipedia.dispatcher :refer [dispatch]]))

(defn show-menu! [] (dispatch :menu/show))
(defn hide-menu! [] (dispatch :menu/hide))
