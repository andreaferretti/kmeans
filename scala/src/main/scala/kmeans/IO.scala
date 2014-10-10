package kmeans

import java.io.FileWriter
import scala.io.Source

import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._


object IO {
  implicit val formats = DefaultFormats

  def toFile(points: List[(Double, Double)], fileName: String) = {
    val lists = points map { case (a, b) => List(a, b) }
    val fw = new FileWriter(fileName)
    fw.write(compact(lists))
    fw.close()
  }

  def fromFile(fileName: String) = {
    val json = Source.fromFile(fileName).mkString

    parse(json).extract[List[List[Double]]] map { case List(a, b) => (a, b) } toSet
  }
}