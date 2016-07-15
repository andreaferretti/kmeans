package kmeans

import java.lang.Math.sqrt

object Algo {
  val n = 10
  val iters = 15

  class Point(val x: Double, val y: Double) {
    def /(k: Double): Point = new Point(x / k, y / k)

    def +(p: Point) = new Point(x + p.x, y + p.y)
    def -(p: Point) = new Point(x - p.x, y - p.y)

    def modulus = sqrt(sq(x) + sq(y))
  }

  def run(xs: List[Point]) = {
    var centroids = xs take n

    var _iters = iters
    while (_iters > 0) {
      centroids = clusters(xs, centroids) map average

      _iters -= 1
    }

    /*
    import scalanative.native._, stdlib._, stdio._
    var i = 0
    while (i < n) {
      fprintf(stdout, c"Centroid [x : %lf, y: %lf]\n", centroids(i).x, centroids(i).y)
      i+=1
    }
    */

    clusters(xs, centroids)
  }

  def clusters(xs: List[Point], centroids: List[Point]) = {
    val ps = xs map ( x => (closest(x, centroids), x) )

    centroids.map(c => ps.filter(_._1 == c).map(_._2) )
  }

  def closest(x: Point, choices: List[Point]) =
    choices minBy { y => dist(x, y) }

  def sq(x: Double) = x * x

  def dist(x: Point, y: Point) = (x - y).modulus

  def average(xs: List[Point]) = xs.reduce(_ + _) / xs.size
}
