import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.paint.Color._
import scalafx.scene.layout.Pane
import scalafx.scene.text.Font
import scalafx.scene.canvas.Canvas
import scala.collection.mutable

class lineChart(root: Pane, data: mutable.Buffer[Any]) {
  //Creation of a canvas.
  val canvas = new Canvas(1000, 1000) //Give width and height as a parameter.

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
  val sortedData = dataList.sortBy(x => x._1)
  //Getting the GraphicsContext
  val g = canvas.graphicsContext2D

  if(xCoordMin + 9 >= xCoordMax) throw new Exception("xCoordMax must be bigger by at least 10, than xCoordMin.")
  if(yCoordMin + 9 >= yCoordMax) throw new Exception("yCoordMax must be bigger by at least 10, than yCoordMin.")
  //Simple drawing.
  g.fill = White //Set fill color.
  g.strokeLine(200,295,200,710)
  g.strokeLine(190,700,805,700)
  g.fill = Black
  g.font = new Font(20) //Set text size.
  g.fillText(xCoordName, 770, 730) //Fill text at (10, 100)
  g.fill = Black
  g.font = new Font(20) //Set text size.
  g.fillText(yCoordName, 170, 270) //Fill text at (10, 100)
  g.fill = Black
  g.font = new Font(10) //Set text size.
  g.fillText(xCoordMin.toString, 204, 710) //Fill text at (10, 100)
  g.fill = Black
  g.font = new Font(10) //Set text size.
  g.fillText(yCoordMin.toString, 180, 695) //Fill text at (10, 100)
  for (i <- 1 to 10) {
    val x = 200 + 60*i
    val y = 300 + 40*(i-1)
    g.fill = Black
    g.font = new Font(10) //Set text size.
    g.fillText((xCoordMin + i*(xCoordMax - xCoordMin)/10).toString, x+4, 710) //Fill text at (10, 100)
    g.strokeLine(x,690,x,710)
    if(hasGrid) {
      g.strokeLine(x,295,x,710)
     }
    g.fill = Black
    g.font = new Font(10) //Set text size.
    g.fillText((yCoordMin + ((yCoordMax - yCoordMin)/10)*(11-i)).toString, 180, y-5) //Fill text at (10, 100)
    g.strokeLine(190,y,210,y)
    if(hasGrid) {
      g.strokeLine(190,y,805,y)
    }
  }
    val coordLengthX = xCoordMax - xCoordMin
    val coordLengthY = yCoordMax - yCoordMin
    def dataPointXInCanvas(point: Int): Int = ((point-xCoordMin)*((800-200).toDouble/coordLengthX) + 200).toInt
    def dataPointYInCanvas(point: Int): Int = (yCoordMax-point)*(700-300)/coordLengthY+300

    val circleRadius = 6
//    g.fill = Red
//    g.fillOval(dataPointXInCanvas(90)-circleRadius/2, dataPointYInCanvas(10)-circleRadius/2, circleRadius, circleRadius)
    for (point <- sortedData) {
      if(point._1 < xCoordMin || point._1 > xCoordMax) throw new Exception("There are x values that are out of bounds.")
      if(point._2 < yCoordMin || point._2 > yCoordMax) throw new Exception("There are y values that are out of bounds.")
      g.fill = Red
      g.fillOval(dataPointXInCanvas(point._1)-circleRadius/2, dataPointYInCanvas(point._2)-circleRadius/2, circleRadius, circleRadius)
    }
  for (i <- 0 to sortedData.length-2) {
    var next = i + 1
    g.setStroke(Green)
    g.strokeLine(dataPointXInCanvas(sortedData(i)._1), dataPointYInCanvas(sortedData(i)._2), dataPointXInCanvas(sortedData(next)._1), dataPointYInCanvas(sortedData(next)._2))
  }


  root.children += canvas //Add canvas to GUI.
}