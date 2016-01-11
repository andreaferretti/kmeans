use "collections"

class Kmeans

  new create() =>
    None

  fun dist(p1: Point, p2: Point): F64 =>
    (p1 - p2).modulus()

  fun average(xs: Array[Point]): Point =>
    var tmp = Point(0,0)
    for p in xs.values() do
      tmp + p
    end 
    tmp / xs.size().f64()

  fun closest(x: Point, choices: Array[Point]): Point =>
    var tmp = Point(0, 0)
    var min = F64.max_value()
    for p in choices.values() do
      let tmp_dist = dist(p, x)
      if tmp_dist < min then
        min = tmp_dist
        tmp = p
      end
    end
    tmp

  fun clusters(xs: Array[Point], centroids: Array[Point]): Map[Point, Array[Point]] =>
    let hm: Map[Point, Array[Point]] = hm.create()
    for c in centroids.values() do
      hm(c) = Array[Point]()
    end

    for p in xs.values() do
      let clo = closest(p, centroids)
      try
        hm.update(clo, hm(clo).push(p))
      else
        hm(clo) = Array[Point]().push(p)
      end
    end

    hm
    
  fun run(xs: Array[Point], n: USize, iters: USize, env: Env): Array[Point] =>
    var centroids = Array[Point]()
    var j: USize = 0
    var i: USize = 0
    while i < n do
      try
        centroids.push(xs(i))
      end
      i = i + 1
    end

    i = 1
    while i <= iters do
      let m = clusters(xs, centroids)
      j = 0
      let newCentroids = Array[Point]()
      while j < n do
        try
          newCentroids.push(average(m(centroids(j))))
        end
        j = j + 1
      end

      centroids = newCentroids
      /*
      //To print
      env.out.print("Centroids at step "+i.string())
      for p in centroids.values() do
        env.out.print(p.string())
      end
      env.out.print("\n")
      */
      i = i + 1
    end

    centroids
