package kmeans

import scalanative.native._, stdlib._, stdio._

import Jansson._
import SysTime._
import Algo.Point

object Main {

 def main(args: Array[String]): Unit = {

    val error = malloc(512).cast[Ptr[json_error_t]]

    val json = json_load_file(c"../points.json", 0, error)

    if ((!json).typ != 1) {
      fprintf(stdout, c"Error parsing Json file")
      return
    }

    val xs = new Array[Point](100000)
    var i = 0

    while (i < 100000) {
      val value = json_array_get(json, i)
      val x = json_number_value(json_array_get(value,0))
      val y = json_number_value(json_array_get(value,1))

      xs(i) = new Point(x, y)

      i+=1
    }

    val before = malloc(sizeof[timeval]).cast[Ptr[timeval]]
    val after = malloc(sizeof[timeval]).cast[Ptr[timeval]]

    gettimeofday(before, null)

    val iterations = 100
    i = 0
    while (i < iterations) {
      Algo.run(xs.toList)
      i+=1
    }
    gettimeofday(after, null)

    val res =
      ((((!after).tv_sec - (!before).tv_sec) * 1000) +
      (((!after).tv_usec - (!before).tv_usec) / 1000)) / iterations

    fprintf(stdout, c"Average time is %d ms\n", res)
  }
}
