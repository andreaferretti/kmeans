import Foundation

let path = "../points.json"
let jsonFile = NSData(contentsOfFile: path)!
let jsonData = (try? NSJSONSerialization.JSONObjectWithData(jsonFile, options:  NSJSONReadingOptions.MutableContainers))!


let points = (jsonData as! Array<protocol<>>)

let xs = points.map({(elem) -> Point in
  let arr = elem as! Array<protocol<>>
  let xValue = arr[0] as! Double
  let yValue = arr[1] as! Double
  return Point(x: xValue, y: yValue)
})

let iterations = 100

let start = NSDate()
for _ in 0..<iterations {
  run(xs, n: 10, iters: 15)
}

let avgTime = start.timeIntervalSinceNow * -1000.0 / Double(iterations)

print("Average time is \(avgTime) ms")
