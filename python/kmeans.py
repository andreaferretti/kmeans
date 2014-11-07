from collections import defaultdict
from math import sqrt
from time import time
import json


class Point(object):
    x = None
    y = None
    def __init__(self, x,y):
        self.x = x
        self.y = y
    def x(self):
        return self.x
    def y(self):
        return self.y
    def __add__(self,other):
        return Point(self.x + other.x, self.y + other.y)
    def __div__(self,value):
        return Point(self.x/float(value),self.y/float(value))
    def dist(self,other):
        return sqrt((self.x - other.x)**2 + (self.y - other.y)**2)
    def closest(self,points):
        return min(points,key=self.dist)
    def __repr__(self):
        return "(%f,%f)" % (self.x,self.y)

def update_centroids(points,centroids):
    groups = groupby(points,centroids)
    res = []
    for g in groups.values():
        res.append(sum(g, Point(0,0))/len(g))
    return res

def groupby(points,centroids):
    g = defaultdict(list)
    for p in points:
        c = p.closest(centroids)
        g[c].append(p)
    return g

def run(xs, n, iters=15):
    centroids = xs[:n]
    for i in xrange(iters):
        centroids = update_centroids(xs,centroids)
    return groupby(xs,centroids)

if __name__ == "__main__":
    with open("../points.json") as f:
        points = map(lambda x: Point(x[0],x[1]),json.loads(f.read()))
    iterations = 100
    start = time()
    for i in xrange(iterations):
        run(points, 10)
    total = (time() - start) * 1000 / iterations
    print("Made {:d} iterations with an average of {:.2f} milliseconds".format(iterations, total))