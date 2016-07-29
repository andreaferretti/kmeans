import java.io.File
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper

//--------------------------------------------------------------------
//-- Constants -------------------------------------------------------
//--------------------------------------------------------------------

val N = 10
val times = 15
val iterations = 100

//--------------------------------------------------------------------
//-- Tools -----------------------------------------------------------
//--------------------------------------------------------------------

fun <T> millisToRun(thunk: () -> T): Pair<T,Long>
{
  val start = System.currentTimeMillis()
  val result = thunk()
  val time = System.currentTimeMillis() - start
  return Pair(result, time)
}

// specialized (and faster) version of 'minBy'
fun <V, T: Collection<V>> T.minBy(selector: (pt: V) -> Double): V
{
  var minE = this.first()
  var minV = Double.MAX_VALUE
  for (e in this) {
    val v = selector(e)
    if (v < minV) {
      minV = v
      minE = e
    }
  }
  return minE
}

//--------------------------------------------------------------------
//-- Point -----------------------------------------------------------
//--------------------------------------------------------------------

data class Point(val x: Double, val y: Double)
{
  operator fun plus(pt: Point) =
    Point(this.x + pt.x, this.y + pt.y)

  operator fun div(d: Double) =
    Point(this.x / d, this.y / d)

  fun distanceTo(pt: Point): Double
  {
    val x = this.x - pt.x
    val y = this.y - pt.y
    return Math.sqrt((x * x) + (y * y))
  }
}

//--------------------------------------------------------------------
//-- Points ----------------------------------------------------------
//--------------------------------------------------------------------

typealias Points = List<Point>

fun Points.closestTo(pt: Point) =
  this.minBy(pt::distanceTo)

fun Points.average(): Point =
  this.reduce(Point::plus) / this.size.toDouble()

//--------------------------------------------------------------------
//-- Main ------------------------------------------------------------
//--------------------------------------------------------------------

fun readPoints(path: String): Points
{
  val mapper = ObjectMapper()
  val typeref = object : TypeReference<List<List<Double>>>() {}
  val list = mapper.readValue<List<List<Double>>>(File(path), typeref)
  return list.map { Point(it[0], it[1]) }
}

fun run(points: Points): Points =
  (1 .. times).fold( points.take(N) ) { centroids, ix ->
    points.groupBy(centroids::closestTo)
          .values
          .map(Points::average)
  }

fun main(args: Array<String>)
{
  val points = readPoints("../points.json")
  val (centroids, time) = millisToRun {
    (2 .. iterations).forEach { run(points) }
    run(points)
  }
  print("ran $iterations iterations, ")
  println("average of ${time / iterations} milliseconds.")
  centroids.forEach(::println)
}

//--------------------------------------------------------------------
