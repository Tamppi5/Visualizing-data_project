import scala.reflect.io.File
import io.circe.generic.auto._
import io.circe.syntax._
import io.circe.parser.decode

import scala.collection.mutable

class ParseData(jsonString: String) {

  case class Chart(chartType: String, xCoordName: String, yCoordName: String,
                   xCoordMax: Int, xCoordMin: Int, yCoordMax: Int,
                   yCoordMin: Int, dataPoints: List[dataPoint], hasGrid: Boolean)

  case class dataPoint(val x: Int, val y: Int)

  //val fileSource   = util.Try(scala.io.Source.fromFile(file.toString))
  val stringSource = util.Try(scala.io.Source.fromString(jsonString))

  def readText(source: scala.io.Source ) = source.getLines().mkString("\n")
  def readJson(text: String ) = decode[List[Chart]](text).toTry

  val data: mutable.Buffer[Any] = mutable.Buffer()
  val charts ={

  val temp = mutable.Buffer[Chart]()
  for {
    source <- stringSource     // Get succesful source result
    text   =  readText(source) // Get text from source
    list   <- readJson(text)   // Parse JSON into list
    chart <- list
  } temp += chart
  temp
}
  for(chart <- charts) {
    data += chart.chartType
    data += chart.xCoordName
    data += chart.yCoordName
    data += chart.xCoordMax
    data += chart.xCoordMin
    data += chart.yCoordMax
    data += chart.yCoordMin
    data += chart.dataPoints.map(a => (a.x, a.y))
    data += chart.hasGrid

      val xCoordName = data(1).toString
      val yCoordName = data(2).toString
      val xCoordMax = data(3) match {
        case int: Int => int
        case _ => throw new Exception("xCoordMax should be an integer.")
      }
      val xCoordMin = data(4) match {
        case int: Int => int
        case _ => throw new Exception("xCoordMin should be an integer.")
      }
      val yCoordMax = data(5) match {
        case int: Int => int
        case _ => throw new Exception("yCoordMax should be an integer.")
      }
      val yCoordMin = data(6) match {
        case int: Int => int
        case _ => throw new Exception("yCoordMin should be an integer.")
      }
      val dataList = data(7) match {
        case lista: List[(Int, Int)] => lista
        case _ => throw new Exception("datalist should be a list containing tuples of integers (Int, Int).")
      }
      val hasGrid = data(8) match {
        case bool: Boolean => bool
        case _ => false
      }
    if(charts.size != 1) {
      throw new Exception("json file was not in correct format")
    }
  }

  data
}
