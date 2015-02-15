(ns kmeans.algo)

(defn sq ^double [^double x] (* x x))

(defn v- [[^double a ^double b] [^double c ^double d]]
  [(- a c) (- b d)])

(defn v+ [[^double a ^double b] [^double c ^double d]]
  [(+ a c) (+ b d)])

(defn vdiv [[^double a ^double b] ^double k]
  [(/ a k) (/ b k)])

(defn norm [[^double a ^double b]]
  (Math/sqrt (+ (sq a) (sq b))))

(defn dist [v w] (norm (v- v w)))

(defn closest [x choices]
  (apply min-key #(dist x %) choices))

(defn avg [xs]
  (vdiv
    (reduce v+ xs)
    (count xs)))

(defn clusters [xs centroids]
  (vals (group-by #(closest % centroids) xs)))

(defn run [points n iters]
  (let
    [centroids-seq (iterate
      (fn [centroids]
        (map avg (clusters points centroids)))
      (take n points))
    final-centroids (nth centroids-seq iters)]
    (clusters points final-centroids)))
