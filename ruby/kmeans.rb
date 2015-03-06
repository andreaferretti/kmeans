require 'json'

class Point
  attr_accessor :x
  attr_accessor :y

  def initialize(x, y)
    @x = x
    @y = y
  end

  def +(other)
    Point.new @x + other.x, @y + other.y
  end

  def /(value)
    Point.new @x / value, @y / value
  end

  def dist(other)
    Math.sqrt((@x - other.x) ** 2 + (@y + other.y) ** 2)
  end

  def closest(points)
    points.min { |a, b|
      dist(a) <=> dist(b)
    }
  end
end

def update_centroids(points, centroids)
  groups = groupby(points, centroids)
  res = []
  groups.each { |k, v|
    res << v.inject(:+)
  }
  res
end

def groupby(points, centroids)
  g = Hash.new []
  points.each { |p|
    c = p.closest(centroids)
    g[c] << p
  }
  g
end

def run(xs, n, iters=15)
  centroids = xs.take(n)
  p centroids
  (1..iters).each {
    centroids = update_centroids(xs, centroids)
  }
  groupby(xs, centroids)
end

points = JSON.parse(open("../points.json").read).map { |p|
  Point.new p[0], p[1]
}

iterations = 100
start = Time.now
(1..iterations).each {
  run(points, 10)
}
total = (Time.now - start) * 1000 / iterations
puts "Made #{iterations} iterations with an average of #{total} milliseconds"
