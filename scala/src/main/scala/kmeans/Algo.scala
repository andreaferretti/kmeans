package kmeans

import math.sqrt


object Algo {
  type Point = (Double, Double)
  val n = 10
  val iters = 15

  implicit class RichPoint(val x: Point) extends AnyVal {
    def /(k: Double): Point = (x._1 / k, x._2 / k)

    def +(y: Point) = ((x._1 + y._1), (x._2 + y._2))
    def -(y: Point) = ((x._1 - y._1), (x._2 - y._2))

    def modulus = sqrt(sq(x._1) + sq(x._2))
  }

  def run(xs: Set[Point]) = {
    var centroids = xs take n

    for (i <- 1 to iters) {
      centroids = clusters(xs, centroids) map average
    }
    clusters(xs, centroids)
  }

  def clusters(xs: Set[Point], centroids: Set[Point]) =
    (xs groupBy { x => closest(x, centroids) }).values.toSet

  def closest(x: Point, choices: Set[Point]) =
    choices minBy { y => dist(x, y) }

  def sq(x: Double) = x * x

  def dist(x: Point, y: Point) = (x - y).modulus

  def average(xs: Set[Point]) = xs.reduce(_ + _) / xs.size
}