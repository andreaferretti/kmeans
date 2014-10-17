package kmeans

import scala.io.Source
import org.json4s._
import org.json4s.jackson.JsonMethods._


object Main extends App {
  def readPoints(path: String) = {
    val json = Source.fromFile(path).mkString
    implicit val formats = DefaultFormats

    parse(json).extract[List[List[Double]]] map { case List(a, b) => (a, b) } toSet
  }

  val iterations = 100
  val points = readPoints("../points.json")
  val start = System.currentTimeMillis
  for (i <- 1 to iterations) {
    Algo.run(points)
  }
  val time = (System.currentTimeMillis - start) / iterations

  println(s"Made $iterations iterations with an average of $time milliseconds")
}