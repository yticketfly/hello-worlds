
lazy val root = (project in file(".")).enablePlugins(PlayScala)
  .settings(
    name := "play-hello",
    scalaVersion := "2.11.7",   
    unmanagedResourceDirectories in Compile <+= baseDirectory ( _ / "data" ),
    version := "1.0.0-SNAPSHOT"
  )

libraryDependencies ++= Seq(
  "com.typesafe.play" % "play_2.11" % "2.4.2",
  "net.sf.barcode4j" % "barcode4j" % "2.0"
)
