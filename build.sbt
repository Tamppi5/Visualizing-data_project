ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "TapioTuloisela_visualizing_data"
  )

// Add dependency on ScalaFX library
libraryDependencies += "org.scalafx" %% "scalafx" % "14-R19"
libraryDependencies += "io.circe" % "circe-generic_2.13" % "0.14.1"
libraryDependencies += "io.circe" % "circe-parser_2.13" % "0.14.1"
libraryDependencies += "io.circe" % "circe-core_2.13" % "0.14.1"

// Determine OS version of JavaFX binaries
lazy val osName = System.getProperty("os.name") match {
case n if n.startsWith("Linux") => "linux"
case n if n.startsWith("Mac") => "mac"
case n if n.startsWith("Windows") => "win"
case _ => throw new Exception("Unknown platform!")
}

// Add JavaFX dependencies, as these are required by ScalaFx
lazy val javaFXModules = Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
libraryDependencies ++= javaFXModules.map( m=>
"org.openjfx" % s"javafx-$m" % "14.0.1" classifier osName
)
