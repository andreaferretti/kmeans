module Kmeans where
import Data.List (minimumBy)
import Data.Ord (comparing)
import Data.Map (Map, fromListWith, elems)
import Debug.Trace (trace)
import Point

closest :: Point -> [Point] -> Point
closest p qs = minimumBy (comparing (dist p)) qs

groupBy :: Ord b => (a -> b) -> [a] -> Map b [a]
groupBy f xs = fromListWith (++) [(f x, [x]) | x <- xs]

clusters :: [Point] -> [Point] -> [[Point]]
clusters ps centroids = elems $ groupBy (flip closest centroids) ps

run :: [Point] -> Int -> Int -> [[Point]]
run ps iters n = step ps centroids iters
  where
    centroids = take n ps
    step ps centroids iters =
      let newClusters = clusters ps centroids in
      if iters == 0 then newClusters -- trace (show centroids) newClusters
      else step ps (map average newClusters) (iters - 1)