(ns webkipedia.ui.page-header
  (:require [clojure.string :as string]))

(defn page-header [{:keys [title description]}]
  [:.PageHeader
   [:h1.PageHeader-title
    (if title (string/replace title #"_" " ") "")]
   [:h6.PageHeader-description (or description "")]])
