package kmeans

import scala.io.Source
import scala.scalajs.js


object Main extends js.JSApp {
  def readPoints(path: String)(run: List[Algo.Point] => Unit) = {
    val fs = js.Dynamic.global.require("fs")

    fs.readFile(path, "utf8", (err: js.Dynamic,data: js.Dynamic) => {
      val arr = js.JSON.parse(data.toString).asInstanceOf[js.Array[js.Array[Double]]]

      run(
        arr.map(p =>
          new Algo.Point(p(0).toDouble, p(1).toDouble)
        ).toList
      )
    })
  }

  def main() = {
    val iterations = 100
    readPoints("../points.json"){ points =>
      val start = System.currentTimeMillis
      for (i <- 1 to iterations) {
        Algo.run(points)
      }
      val time = (System.currentTimeMillis - start) / iterations

      println(s"Made $iterations iterations with an average of $time milliseconds")
    }
  }
}
