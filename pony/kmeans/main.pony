use "collections"
use "time"
use "files"
use "json"

type Point is (F64, F64)

actor Main

  let n          : USize = 10
  let iters      : USize = 15
  let iterations : USize = 100

  let env        : Env
  var before     : U64 = 0
  let points     : Array[Point] = Array[Point](65536)
  var centroids  : Array[Point] = Array[Point](n)

  new create(env': Env) =>
    env = env'
    readPointsFromJsonFile()
    before = Time.millis()
    for it in Range(0, iterations) do
      run()
    end
    done()

  fun ref readPointsFromJsonFile() =>
    try
      let f  = File.open(FilePath(env.root, "../points.json"))
      var fileString = ""
      for line in f.lines() do
        fileString = fileString + line
      end
      let doc : JsonDoc = JsonDoc
      doc.parse(fileString)
      for el in (doc.data as JsonArray).data.values() do
        let x = ((el as JsonArray).data(0) as F64)
        let y = ((el as JsonArray).data(1) as F64)
        points.push((x, y))
      end
    end

  be run() =>
    points.copy_to(centroids, 0, 0, n)
    for it in Range(0, iters) do
      cluster()
    end

  fun printCentroids() =>
    for c in centroids.values() do
      env.out.print("(x: " + c._1.string() +
                    ", y: " + c._2.string() + ")")
    end

  be done() =>
    let t = (Time.millis() - before) / iterations.u64()
    printCentroids()
    env.out.print("Average time is " + t.string() + " ms.")

  fun ref cluster() =>
    try
      let hm: MapIs[Point, Array[Point]] = hm.create(n)
      for ix in Range(0, n) do
        hm(centroids(ix)) = Array[Point](2048)
      end
      for p in points.values() do
        hm(closestCentroid(p)).push(p)
      end
      for ix in Range(0, n) do
        centroids.update(ix, average(hm(centroids(ix))))
      end
    end

  fun ref closestCentroid(x: Point): Point =>
    var min_pt : Point = (0, 0)
    var min_dist = F64.max_value()
    try
      for it in Range(0, n) do
        let pt = centroids(it)
        let distance = dist(pt, x)
        if distance < min_dist then
          min_dist = distance
          min_pt = pt
        end
      end
    end
    min_pt

  fun dist(p1: Point, p2: Point): F64 =>
    let x = p1._1 - p2._1
    let y = p1._2 - p2._2
    ((x * x) + (y * y)).sqrt()

  fun average(pts: Array[Point]): Point =>
    var x : F64 = 0
    var y : F64 = 0
    for p in pts.values() do
      x = x + p._1
      y = y + p._2
    end
    let nrOfPoints = pts.size().f64()
    (x / nrOfPoints, y / nrOfPoints)

