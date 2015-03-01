module Main where
import Text.JSON (decode, Result(..))
import Text.Printf (printf)
import System.CPUTime (getCPUTime)
import Control.DeepSeq (deepseq)
import Point (Point(..))
import Kmeans (run)

readPoints :: String -> [Point]
readPoints string = map readPoint xs
  where
    xs = case (decode string :: Result [[Double]]) of
      Ok t -> t
      _ -> error "Invalid JSON"
    readPoint [x, y] = Point x y

main = do
  content <- readFile "../points.json"
  let ps = readPoints content
  start <- ps `deepseq` getCPUTime -- in picoseconds, deepseq forces evaluation
  let clusters = run ps 15 10 -- how to run 100 times?
  end <- clusters `deepseq` getCPUTime
  let diff = (fromIntegral (end - start)) / (10^9)
  printf "Made 1 iteration in %0.1f ms\n" (diff :: Double)