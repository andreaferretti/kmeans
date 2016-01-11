use "time"
use "json"
use "files"

actor Main
  let xs: Array[Point] = Array[Point]()
  var centroids: Array[Point] = Array[Point]()

  let n: USize = 10
  let iters: USize = 15

  let iterations: U64 = 100

  new create(env: Env) =>
    let caps = recover val FileCaps.set(FileRead).set(FileStat) end

    var fileString = ""
    try
      with file = OpenFile(FilePath(env.root, "../points.json", caps)) as File do
        for line in file.lines() do
          fileString = fileString + line
        end
      end
    end

    let doc: JsonDoc = JsonDoc
    try
      doc.parse(fileString)

      for el in (doc.data as JsonArray).data.values() do
        let x = ((el as JsonArray).data(0) as F64)
        let y = ((el as JsonArray).data(1) as F64)
        xs.push(Point(x, y))
      end
    end

    let before = Time.millis()

    var i: U64 = 0
    while i < iterations do
      iteration(i, before, env)
      i = i + 1
    end

  be iteration(ite: U64, initTime: U64, env: Env) =>
    let kmeans = Kmeans.create()
    centroids = kmeans.run(xs, n, iters, env)

    if (ite == (iterations - 1)) then
      printAndQuit(initTime, env)
    end
    
  be printAndQuit(initTime: U64, env: Env) =>
    let after = Time.millis()
    
    env.out.print("Final centroids")
    for p in centroids.values() do
      env.out.print(p.string())
    end
    
    let t = (after - initTime) / iterations
    env.out.print("Average time is "+ t.string())

