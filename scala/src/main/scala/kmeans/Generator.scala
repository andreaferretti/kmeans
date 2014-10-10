package kmeans

import math.sqrt
import scala.util.Random.{ nextDouble, shuffle }


object Generator {
  type Point = (Double, Double)
  val n = 10
  val size = 100000

  implicit class RichPoint(val x: Point) extends AnyVal {
    def +(y: Point) = ((x._1 + y._1), (x._2 + y._2))
  }

  def nextPoint = (3 * nextDouble, 3 * nextDouble)

  def cloud(p: Point) = (1 to (size / n)) map { _ =>
    p + (nextDouble - 0.5, nextDouble - 0.5)
  }

  def run = {
    val points = for {
      center <- (1 to n) map {_ => nextPoint}
      point <- cloud(center)
    } yield point

    shuffle(points.toList)
  }

}