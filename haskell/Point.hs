module Point where
import GHC.Float (int2Double)
import Control.DeepSeq (NFData)

data Point = Point Double Double deriving (Show, Eq)

(+.) :: Point -> Point -> Point
(Point x1 y1) +. (Point x2 y2) = Point (x1 + x2) (y1 + y2)

(-.) :: Point -> Point -> Point
(Point x1 y1) -. (Point x2 y2) = Point (x1 - x2) (y1 - y2)

(/.) :: Point -> Double -> Point
(Point x y) /. k = Point (x / k) (y / k)

norm :: Point -> Double
norm (Point x y) = sqrt $ (x * x) + (y * y)

dist :: Point -> Point -> Double
dist p q = norm $ p -. q

psum :: [Point] -> Point
psum = foldl (+.) (Point 0 0)

average :: [Point] -> Point
average ps = (psum ps) /. int2Double (length ps)

instance Ord Point where
  (Point x1 y1) `compare` (Point x2 y2) =
    let c = x1 `compare` x2 in
      if c == EQ then y1 `compare` y2 else c

instance NFData Point -- needed to force evaluation