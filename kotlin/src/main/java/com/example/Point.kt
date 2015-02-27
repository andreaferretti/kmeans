package com.example

/**
 * Created by evacchi on 26/02/15.
 */
data class Point(var x: Double, var y: Double) {

    fun div(d: Double) = Point(x/d, y/d)
    fun divAssign(d: Double) {
        x /= d
        y /= d
    }
    fun plus(p2: Point) = Point(x+p2.x, y+p2.y)
    fun plusAssign(p2: Point) {
        x += p2.x
        y += p2.y
    }
    fun minus(p2: Point) = Point(x-p2.x, y-p2.y)
    fun minusAssign(p2: Point) {
        x -= p2.x
        y -= p2.y
    }
    private fun sq(x: Double): Double = x*x

    public val modulus: Double = Math.sqrt(sq(x) + sq(y))

}


