package kmeans

import scala.util.Try


object Main extends App {
  type Point = (Double, Double)
  val iterations = 100

  if (Try(args(0)).toOption == Some("write")) {
    IO.toFile(Generator.run, "../points.json")
  }
  else {
    val points: Set[Point] = IO.fromFile("../points.json")

    val start = System.currentTimeMillis
    for (i <- 1 to iterations) {
      Algo.run(points)
    }
    val time = (System.currentTimeMillis - start) / iterations

    println(s"Made $iterations iterations with an average of $time milliseconds")
  }
}