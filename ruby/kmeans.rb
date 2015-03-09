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
    Point.new @x / value.to_f, @y / value.to_f
  end

  def dist(other)
    Math.sqrt((@x - other.x) ** 2 + (@y - other.y) ** 2)
  end

  def closest(points)
    points.min { |a, b|
      self.dist(a) <=> self.dist(b)
    }
  end

  def inspect
    '(%.6f,%.6f)' % [@x, @y]
  end
end

def update_centroids(points, centroids)
  groups = groupby(points, centroids)
  res = []
  groups.each { |k, v|
    res << v.inject(Point.new(0,0), :+) / v.length
  }
  res
end

def groupby(points, centroids)
  g = Hash.new {|h,k| h[k]=[]}
  points.each { |pp|
    c = pp.closest(centroids)
    g[c] << pp
  }
  g
end

def run(xs, n, iters=15)
  centroids = xs.take(n)
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
puts 'Made % iterations with an average of %.6f milliseconds' % [iterations, total]
