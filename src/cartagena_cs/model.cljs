(ns cartagena-cs.model
  (:require
    [reagent.core :as reagent]))

(defonce game-state (reagent/atom {}))

(defn update-player-name! [name]
  (swap! game-state assoc :player-name name))

(defn update-uid! [uid]
  (swap! game-state assoc :uid uid))

(defn update-server-state! [server-state]
  (swap! game-state assoc :server-state server-state))

(defn update-joining-game-token! [token]
  (swap! game-state assoc :joining-game-token token))

(defn select-card! [card]
  (swap! game-state assoc :selected-card card))

(defn unselect-card! []
  (swap! game-state dissoc :selected-card))

(defn card-played [game-state server-state]
  (-> game-state
      (dissoc :selected-card)
      (assoc :server-state server-state)))

(defn card-played! [server-state]
  (println "card-played! server-state:" server-state)
  (swap! game-state card-played server-state))

(defn moved-back! [server-state]
  (println "moved-back! server-state:" server-state)
  (swap! game-state assoc :server-state server-state))

(defn game-over? []
  (let [board (get-in @game-state [:server-state :board])
        ship (first (filter #(= :ship (:icon %)) board))
        pirate-counts-by-color (frequencies (:pirates ship))]
    (some #(>= (second %) 6) pirate-counts-by-color)))

(defn end-game! []
  (swap! game-state assoc :game-over true))
