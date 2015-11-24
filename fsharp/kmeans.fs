module Kmeans

type Point = { x: double; y: double }

let sq (x: double) = x*x

type Point with
  static member (+) (p1: Point, p2: Point) =
    { x = p1.x + p2.x; y = p1.y + p2.y }

  static member (-) (p1: Point, p2: Point) =
    { x = p1.x - p2.x; y = p1.y - p2.y }

  static member (*) (p1: Point, p2: Point) =
    { x = p1.x * p2.x; y = p1.y * p2.y }

  static member (/) (p: Point, d: double) =
    { x = p.x / d; y = p.y / d }

  static member Zero = { x = 0.0; y = 0.0 }

  member this.modulus = sqrt ( sq(this.x) + sq(this.y))

let dist (p1: Point, p2: Point) = (p1 - p2).modulus

let average (xs: Point list) = (xs |> List.sum) / (xs.Length |> double)

let closest (p: Point, xs: Point list) =
  xs |> List.minBy( fun x -> dist (p, x) )

let clusters (xs: Point list, centroids: Point list) =
  xs |> List.groupBy( fun x -> closest ( x, centroids)) |> List.map snd

let rec run (xs: Point list, centroids: Point list, iters: int) =
  match iters with 
    | 1 -> 
      let finalCentroids = clusters ( xs, centroids) |> List.map average
      //printfn "Final centroids are %A" finalCentroids
      finalCentroids
    | _ -> run (xs, clusters ( xs, centroids) |> List.map average, iters - 1)

let start (xs: Point list, iters: int, n: int) =
  run (xs, xs |> List.take n, iters)
