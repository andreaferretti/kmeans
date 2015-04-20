import times, json, math, strutils, algo

const
  n = 10
  iterations = 100
  filename = "../points.json"

proc loadPoints(): Points =
  result = newSeq[Point]()
  for p in parseFile(filename).items:
    result.add((x: p[0].fnum, y: p[1].fnum))

proc main() =
  let points = loadPoints()
  var centroids : array[n, Point]
  let start = cpuTime()
  for i in 1 .. iterations:
    calculateCentroids(points, centroids)
  let time = (((cpuTime() - start) * 1000) / float(iterations)).round
  echo format("Made $1 iterations with an average of $2 miliseconds",
              iterations, time)
  for centroid in centroids:
    echo centroid

when isMainModule:
  main()
