//import Main.sideBox
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.layout._
import scalafx.scene.layout.HBox
import scalafx.scene.layout.GridPane
import scalafx.scene.layout.Background
import scalafx.scene.layout.BackgroundFill
import scalafx.scene.layout.CornerRadii
import scalafx.scene.layout.ColumnConstraints
import scalafx.scene.layout.RowConstraints
import scalafx.scene.paint.Color._
import scalafx.scene.control.{Button, Label, TextField}

class Data(chartType: Int, box: Pane, nroOfData: Int) {
  val graphButton = new Button("Show graph")
  val nLabel = new Label("\n")
    if(chartType == 0) {
      val newBox = new VBox
      val label = new Label("Write the data below.\n" +
        "Write the bar value per input.\n" +
        "The input has to be an integer.\n" +
        "of range 0-1000. For example\n(340)\n")

      newBox.children += label
      label.text

      for (i <- 1 to nroOfData) {
        var dataNro = i
        newBox.children += new Label("Data input number " + dataNro)
        var textInput = new TextField
        newBox.children += textInput
        textInput.text
      }
      newBox.children += nLabel
      newBox.children += graphButton
      box.children += newBox

  } else if (chartType == 1) {
      val newBox = new VBox
      val label = new Label("Write the data below")

      newBox.children += label
      label.text

      for (i <- 1 to nroOfData) {
        var dataNro = i
        newBox.children += new Label("Data input number " + dataNro)
        var textInput = new TextField
        newBox.children += textInput
        textInput.text
      }
      newBox.children += nLabel
      newBox.children += graphButton
      box.children += newBox

  } else if (chartType == 2) {
      val newBox = new VBox
      val label = new Label("Write the data below")

      newBox.children += label
      label.text

      for (i <- 1 to nroOfData) {
        var dataNro = i
        newBox.children += new Label("Data input number " + dataNro)
        var textInput = new TextField
        newBox.children += textInput
        textInput.text
      }
      newBox.children += nLabel
      newBox.children += graphButton
      box.children += newBox

  } else if (chartType == 3) {
      val newBox = new VBox
      val label = new Label("Write the data below")

      newBox.children += label
      label.text

      for (i <- 1 to nroOfData) {
        var dataNro = i
        newBox.children += new Label("Data input number " + dataNro)
        var textInput = new TextField
        newBox.children += textInput
        textInput.text
      }
      newBox.children += nLabel
      newBox.children += graphButton
      box.children += newBox
    }
}
