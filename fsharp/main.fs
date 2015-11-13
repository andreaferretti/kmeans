open Kmeans
open System.IO
open FSharp.Data
open Newtonsoft.Json
open Newtonsoft.Json.Linq

let file = File.ReadAllText(@"../points.json")

let json = JArray.Parse(file)

let getPoint (e: JToken) =
  { x = (double (e.Item(0).ToString())); y = (double (e.Item(1).ToString())) }

let xs = [ for i in 0 .. (json.Count-1) -> getPoint(json.Item(i)) ]

let iterations = 100

let stopWatch = System.Diagnostics.Stopwatch.StartNew()

for i in 1 .. iterations do
  start (xs, 15, 10) |> ignore

stopWatch.Stop()

let time = stopWatch.Elapsed.TotalMilliseconds / (float iterations)

printfn "Average time is %f" time
