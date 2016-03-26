(ns cs-test.model
  (:require
    [reagent.core :as reagent]))

(defonce game-state (reagent/atom {}))

(defn update-player-name! [name]
  (swap! game-state assoc :player-name name))

(defn update-server-state! [server-state]
  (swap! game-state assoc :server-state server-state))

(defn update-joining-game-token! [token]
  (swap! game-state assoc :joining-game-token token))
