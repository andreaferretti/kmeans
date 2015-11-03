use "time"
use "json"
use "files"

actor Main
  new create(env: Env) =>
    let xs = Array[Point]()

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

    let n: U64 = 10
    let iters: U64 = 15

    let before = Time.millis()
    //must be increased to 100
    let iterations: U64 = 10

    var i: U64 = 0
    while i < (iterations - 1) do
      Kmeans.run(xs, n, iters, env)
      i = i + 1
    end
    let res = Kmeans.run(xs, n, iters, env)
    let after = Time.millis()

    env.out.print("Final centroids")
    for p in res.values() do
      env.out.print(p.string())
    end

    let t = (after - before) / iterations
    env.out.print("Average time is "+ t.string())
