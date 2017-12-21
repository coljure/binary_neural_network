(ns neural-networks.routes.services.neuron)

(def weight-or (atom (repeatedly 2 rand)))
(def weight-and (atom (repeatedly 2 rand)))
(def theta-or (atom -0.4)) ;; Umbral
(def theta-and (atom -0.4)) ;; Umbral
(def learning-factor 0.5)


(defmulti get-gate (fn [gate] gate))
(defmethod get-gate :and [params]
  {:weight weight-and
   :theta  theta-and
   :logic-table [[-1 -1 -1]
                [-1  1 -1]
                [ 1 -1 -1]
                [ 1  1  1]]})

(defmethod get-gate :or [params]
  {:weight weight-or
   :theta  theta-or
   :logic-table [[-1 -1 -1]
                [-1  1  1]
                [ 1 -1  1]
                [ 1  1  1]]})

(defn valid? [y-expect y-actual]
  (prn "y-expect" y-expect "y-actual" y-actual)
  (= y-expect y-actual))

(defn activation-function [row weights theta]
  (let [output (reduce + (map #(* %1 %2) row weights))
        result (Math/tanh (+ output (* -1 theta)))]
    (prn row "=>" result)
    (if (< result 0) -1 1)))

(defn training [gate]
  (let [gate-info (get-gate gate)
        tt (:logic-table gate-info)
        y-expects (map #(nth % 2) tt)]
    (loop [i 100
           new-weights @(:weight gate-info)
           new-theta @(:theta gate-info)
           [row & rest-tt] (:logic-table gate-info)]
      (prn i "- row ==> " row)
      (if (or (nil? row) (< i 0))
        (do
          (reset! (:weight gate-info) new-weights)
          (reset! (:theta gate-info) new-theta)
          (cons new-theta (seq new-weights)))
        (let [y-expect (nth row 2)
              y-actual (activation-function row new-weights new-theta)
              [w1 w2] new-weights]
          (if (valid? y-expect y-actual)
            (recur (dec i) new-weights new-theta rest-tt)
            (do
              (prn "Create new weights " new-weights "new-tetha" new-theta)
              (recur (dec i)
                     (map (fn [w x] (+ w (* 2 learning-factor y-expect x))) new-weights row)
                     (+ new-theta (* -2 learning-factor y-expect))
                     tt))))))))

(defn get-output [row gate]
  (let [gate-info (get-gate gate)
        result (activation-function (map #(if (= % 0) -1 %) row) @(:weight gate-info) @(:theta gate-info))]
    (prn "row" row @(:weight gate-info) @(:theta gate-info))
    (if (> result 0) 1 0)))

(defn get-current-inputs [gate]
  (let [gate-info (get-gate gate)]
    (cons @(:theta gate-info) @(:weight gate-info))))
