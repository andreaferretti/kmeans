import math, hashes, tables, sequtils

type
  Point* = tuple[x, y: float]
  Points* = seq[Point]

proc hash(p: Point): THash =
  !$(p.x.hash !& p.y.hash)

proc `+`(p, q: Point): Point = (p.x + q.x, p.y + q.y)

proc `-`(p, q: Point): Point = (p.x - q.x, p.y - q.y)

proc `/`(p: Point, k: float): Point = (p.x / k, p.y / k)

proc norm(p: Point): float = sqrt(p.x * p.x + p.y * p.y)

proc dist(p, q: Point): float = norm(p - q)

proc closest(p: Point, qs: Points): Point =
  var minDist = Inf
  for q in qs:
    let d = dist(p, q)
    if d < minDist:
      minDist = d
      result = q

proc groupby(points, centroids: Points): Table[Point, Points] =
  result = initTable[Point, Points]()
  for p in points:
    let c = p.closest(centroids)
    if result.hasKey(c):
      result.mget(c).add(p)
    else:
      result[c] = @[p]

proc average(points: Points): Point =
  return foldl(points, a + b) / float(points.len)

proc newCentroids(points: Points, centroids: Points): Points =
  result = @[]
  let groups = groupby(points, centroids)
  for g in groups.values:
    result.add(average(g))

proc run*(points: Points, n: int, iters: int = 15): seq[Points] =
  result = @[]
  var centroids = points[0 .. <n]
  for i in 0 .. <iters:
    centroids = newCentroids(points, centroids)
  for g in groupby(points, centroids).values:
    result.add(g)
