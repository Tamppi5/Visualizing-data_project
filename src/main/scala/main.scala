import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.event.ActionEvent
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.chart.LineChart
import scalafx.scene.layout.{Background, BackgroundFill, BorderPane, ColumnConstraints, CornerRadii, GridPane, HBox, RowConstraints, StackPane, VBox}
import scalafx.scene.paint.Color._
import scalafx.scene.control.{Button, CheckBox, Label, MenuButton, MenuItem, TextField}
import scalafx.scene.text.Font
import scalafx.stage.FileChooser

import scala.collection.mutable._
import scala.io.Source

object Main extends JFXApp {

  /*
  Creation of a new primary stage (Application window).
  We can use Scala's anonymous subclass syntax to get quite
  readable code.
  */
    case class InvalidJsonData(description: String, errorData: String)
           extends java.lang.Exception(description)
    stage = new JFXApp.PrimaryStage {
      title.value = "Visualizing data"
      width = 1100
      height = 800
  }


    val root = new GridPane //Create GridPane
    val view2 = new StackPane
    val mainScene = new Scene(root) //Scene acts as a container for the scene graph
    stage.scene = mainScene

    //Create some components to fill the grid with
    val bottomBox = new HBox //Horizontal box is like VBox except children are laid in a row.


    val fillBox = new Canvas(300, 300)
    val mainBox = new BorderPane

    val dataBox = new VBox
    val label = new Label("Choose the data:")
    val fileInput = new Button("select file")

    dataBox.children += label
    dataBox.children += fileInput

    val centerBox = new VBox
    centerBox.children += dataBox
    centerBox.setAlignment(Pos.Center)
    dataBox.setAlignment(Pos.Center)
    mainBox.center = centerBox

    /*
    We aim for this.
    +----+----------------------+
    |                           |
    |         choose file       |
    |                           |
    |----+----------------------+
    |                           |
    +----+----------------------+
    */

    //Add child components to grid
    //Method usage: add(child, columnIndex, rowIndex, columnSpan, rowSpan)
//
    root.add(mainBox, 0, 0)
    root.add(bottomBox, 0, 1)

    //Define grid row and column size
    val column0 = new ColumnConstraints

    val row0 = new RowConstraints
    val row1 = new RowConstraints

    column0.percentWidth = 100
    row0.percentHeight = 84
    row1.percentHeight = 16
    //Creation of a canvas.
      //new lineChart(mainBox, Buffer())
    root.columnConstraints = Array[ColumnConstraints](column0) //Add constraints in order
    root.rowConstraints = Array[RowConstraints](row0, row1)

    bottomBox.background = new Background(Array(new BackgroundFill((Gray), CornerRadii.Empty, Insets.Empty))) //Set bottomBox background color

    var jsonDataString2 = ""
    fileInput.onAction = (e:ActionEvent) => {
      val chooseFile = new FileChooser {
        title = "Choose file"
      }
      val chosenFile = chooseFile.showOpenDialog(stage)
      if(chosenFile != null) {
        if (chosenFile.getName.endsWith(".txt")) {
          val lines = Source.fromFile(chosenFile).getLines().toBuffer
          lines.foreach(jsonDataString2 += _ + "\n")
          println(jsonDataString2)
          val parsed = try {
            new ParseData(jsonDataString2)
          } catch {
            case _ => println("json file was not in correct format")
          }
          parsed match {
            case p: ParseData => {
              centerBox.children -= dataBox
              val newData = new VBox
              if(p.data.isEmpty) throw new Exception("JSON was implemented incorrectly.")
              newData.children += new lineChart(centerBox, p.data).canvas
              newData.setAlignment(Pos.Center)
              centerBox.children += newData
            }
            case _ =>
          }
        } else throw new Exception("The chosen file was not a text file (.txt)")
      }
  }
}

trait ValidationError
case object EmptyField extends ValidationError
case object TooLong extends ValidationError