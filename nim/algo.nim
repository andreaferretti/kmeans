import math, hashes, tables, sequtils

type
  Point*    = tuple[x, y: float]
  Points*   = seq[Point]
  Centroids = openarray[Point]

proc hash(p: Point): THash {.noInit.} =
  !$(p.x.hash !& p.y.hash)

proc `+`(p, q: Point): Point {.noInit.} =
  (p.x + q.x, p.y + q.y)

proc `-`(p, q: Point): Point {.noInit.} =
  (p.x - q.x, p.y - q.y)

proc `/`(p: Point, k: float): Point {.noInit.} =
  (p.x / k, p.y / k)

proc norm(p: Point): float {.noInit.} =
  sqrt(p.x * p.x + p.y * p.y)

proc dist(p, q: Point): float {.noInit.} =
  norm(p - q)

proc closest(p: Point, centroids: Centroids): Point {.noInit.} =
  var minDist = Inf
  for centroid in centroids:
    let d = dist(p, centroid)
    if d < minDist:
      minDist = d
      result = centroid

proc groupBy(points: Points, centroids: Centroids): Table[Point,Points] =
  result = initTable[Point, Points]()
  for c in centroids:
    result[c] = @[]
  for point in points:
    let centroid = point.closest(centroids)
    result.mget(centroid).add(point)

proc average(points: Points): Point =
  foldl(points, a + b) / float(points.len)

proc updateCentroids(points: Points, centroids: var Centroids) =
  let groups = points.groupBy(centroids)
  var ix = 0
  for group in groups.values:
    centroids[ix] = average(group)
    ix += 1

proc calculateCentroids*(points: Points, centroids: var Centroids,
                         iterations: int = 15) =
  for ix, x in centroids:
    centroids[ix] = points[ix]
  for i in 1 .. iterations:
    updateCentroids(points, centroids)

