from math import sqrt
from itertools import groupby, izip
from time import time
import json

def grouped(xs, key):
  data = sorted(xs, key=key)
  return [list(v) for k, v in groupby(data, key)]

def sq(x):
  return x * x

def dist(x, y):
  return sqrt(sq(x[0] - y[0]) + sq(x[1] - y[1]))

def vsum(v, w):
  return map(sum, izip(v, w))

def closest(x, choices):
  return min(choices, key = lambda y: dist(x, y))

def clusters(xs, centroids):
  return grouped(xs, lambda x: closest(x, centroids))

def average(xs):
  total = reduce(vsum, xs, [0, 0])
  return [k / len(xs) for k in total]

def run(xs, n, iters=15):
  centroids = xs[0:n]
  for i in xrange(iters):
    centroids = [average(c) for c in clusters(xs, centroids)]
  return clusters(xs, centroids)


if __name__ == "__main__":
  with open("../points.json") as f:
    points = json.loads(f.read())
  iterations = 100
  start = time()
  for i in xrange(iterations):
    run(points, 10)
  total = (time() - start) * 1000 / iterations
  print("Made {:d} iterations with an average of {:.2f} milliseconds".format(iterations, total))
