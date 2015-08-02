(ns webkipedia.ui.css-transition-group
  (:require [reagent.core :as reagent]))

(def css-transition-group
  (reagent/adapt-react-class js/React.addons.CSSTransitionGroup))
