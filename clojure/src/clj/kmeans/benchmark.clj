(ns kmeans.benchmark
  (:require
    [clojure.java.io :as io]
    [cheshire.core :as json])
  (:use [kmeans.algo]))

(defn now []
  (.getTime (java.util.Date.)))

(defn result [ms iters]
  (println (str "The average time was " (/ (float ms) iters) " ms")))

(defn read-points [path]
  (json/parse-stream (io/reader path) true))

(defn -main
  [& args]
    (let [
      n 10
      iters 15
      repeated 100
      points (read-points "../points.json")
      start (now)
      ]
      (dotimes [_ repeated] (run points n iters))
      (result (- (now) start) repeated)
    ))
