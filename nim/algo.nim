import math
import hashes
import tables
import sequtils

type Point* = object
  x*, y*: float

proc hash(p: Point): THash =
  result = p.x.hash !& p.y.hash
  return !$result

proc `+`(p: Point, q: Point): Point = Point(x: p.x + q.x, y: p.y + q.y)

proc `-`(p: Point, q: Point): Point = Point(x: p.x - q.x, y: p.y - q.y)

proc `/`(p: Point, k: float): Point = Point(x: p.x / k, y: p.y / k)

proc norm(p: Point): float = sqrt(p.x * p.x + p.y * p.y)

proc dist(p: Point, q: Point): float = norm(p - q)

proc closest(p: Point, qs: seq[Point]): Point =
  var minDist = Inf
  var d: float
  for q in qs:
    d = dist(p, q)
    if d < minDist:
      minDist = d
      result = q
  return result

proc groupby(points: seq[Point], centroids: seq[Point]): Table[Point, seq[Point]] =
  var
    g = initTable[Point, seq[Point]]()
    c: Point
  for p in points:
    c = p.closest(centroids)
    if g.hasKey(c):
      var s = g[c]
      s.add(p)
    else:
      g[c] = @[p]
  return g

proc average(points: seq[Point]): Point =
  return foldl(points, a + b) / float(points.len)

proc newCentroids(points: seq[Point], centroids: seq[Point]): seq[Point] =
  let groups = groupby(points, centroids)
  var result = newSeq[Point]()
  for g in groups.values:
    result.add(average(g))
  return result

proc run*(points: seq[Point], n: int, iters: int = 15): seq[seq[Point]] =
  var
    centroids = points[0..n-1]
    result: seq[seq[Point]] = @[]
  for i in 0 .. (iters - 1):
    centroids = newCentroids(points, centroids)
  for g in groupby(points, centroids).values:
    result.add(g)