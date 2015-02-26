package com.example

/**
 * Created by evacchi on 26/02/15.
 */

import java.util.ArrayList
import java.util.HashMap


//public fun KMeans(Xs: Array<Point>, n: Int, iters: Int): KMeans {
//    val __ = KMeans(n, iters)
//    __.Xs = Xs
//    return __
//}
//
//public fun KMeans(Xs: Array<Point>): KMeans {
//    val __ = KMeans(0, 0)
//    __.Xs = Xs
//    return __
//}

object KMeans {
    val n = 10
    val iters = 15

    fun run(xs: List<Point>) {
        var centroids = xs take n
        for (i in 1..iters) {
            centroids = clusters(xs, centroids) map { average(it) }
        }
        clusters(xs, centroids)
    }

    fun clusters(xs: List<Point>, centroids: List<Point>) =
            (xs groupBy { x -> closest(x, centroids) }).values().toList()

    fun closest(x: Point, choices: List<Point>) =
        choices minBy { y -> dist(x,y) }

    fun sq(x:Double)=x*x

    fun dist(x:Point, y:Point) = (x-y).modulus

    fun average(xs:List<Point>) = xs.reduce{ (p1, p2) -> p1+p2 }
}
