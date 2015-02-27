package com.example

/**
 * Created by evacchi on 26/02/15.
 */

import java.util.ArrayList
import java.util.HashMap

object KMeans {
    val n = 10
    val iters = 15

    fun run(xs: List<Point>) {
        var centroids: List<Point> = xs take n
        for (i in 1..iters) {
            centroids = clusters(xs, centroids) map { average(it) }
        }
        clusters(xs, centroids)
    }

    fun clusters(xs: List<Point>, centroids: List<Point>) =
            (xs groupBy { x -> closest(x, centroids) }).values().toList()

    fun closest(x: Point, choices: List<Point>) =
        choices minBy { y -> dist(x,y) }

    fun sq(x:Double) = x*x

    fun dist(x:Point, y:Point) = (x-y).modulus

    fun average(xs:List<Point>) = ( xs.reduce{ (p1, p2) -> p1+p2 } ) / xs.size().toDouble()
}
