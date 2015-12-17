import Darwin

struct Point : Equatable {
  let x: Double
  let y: Double


  func modulus() -> Double {
    return sqrt(sq(self.x) + sq(self.y))
  }
}

extension Point: Hashable {
  var hashValue: Int {
      return x.hashValue ^ (y.hashValue << 1)
  }
}

func ==(x: Point, y: Point) -> Bool {
  return (x.x == y.x && x.y == y.y)
}

func +(left: Point, right: Point) -> Point {
  return Point(x: left.x + right.x, y: left.y + right.y)
}

func -(left: Point, right: Point) -> Point {
  return Point(x: left.x - right.x, y: left.y - right.y)
}

func /(left: Point, right: Double) -> Point {
  return Point(x: left.x / right, y: left.y / right)
}

func sq(x: Double) -> Double {
  return x*x
}

func dist(p1: Point, p2: Point) -> Double {
  return ((p1 - p2).modulus())
}

func average(xs: [Point]) -> Point {
  return xs.reduce(Point(x: 0, y: 0), combine: +) / Double(xs.count)
}

func closest(x: Point, choices: [Point]) -> Point {
  func comp(px: (point: Point, distance: Double), py: (point: Point, distance: Double)) -> (point: Point, distance: Double) {
    if (px.distance < py.distance) { return px }
    else { return py }
  }
  let minp = choices.map(
    {(point: $0, distance: (dist($0, p2: x)))}).reduce(
    (point: Point(x: 0, y: 0), distance: DBL_MAX), combine: comp)
  
  return minp.point
}

func clusters(xs: [Point], centroids: [Point]) -> [[Point]] {
  var dict =  [Point: [Point]]()

  for p in xs {
    let close = closest(p, choices: centroids)

    dict[close]?.append(p) ?? {dict[close] = [p]}()
  }
  
  return [[Point]](dict.values)
}

func run(xs: [Point], n: Int, iters: Int) -> [[Point]] {
  var centroids = [Point](xs[0..<n])

  for _ in 0..<iters {
    centroids = clusters(xs, centroids: centroids).map(average)
  }
/*
  print("Final centroids?")
  for c in centroids {
    print(c)
  }
*/    
  return clusters(xs, centroids: centroids)
}
