import times
import json
import math
import strutils

import algo

let
  n = 10
  iterations = 100
  filename = "../points.json"
  content = parseFile(filename)
var points = newSeq[Point]()
for p in content.items:
  points.add((x: p[0].fnum, y: p[1].fnum))
let start = cpuTime()
for i in 0 .. (iterations-1):
  discard run(points, n)
let time = ((cpuTime() - start) * 1000 / float(iterations)).round

echo "Made $1 iterations with an average of $2 milliseconds".format(iterations, time)
