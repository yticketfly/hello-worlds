
lazy val root = (project in file("."))
  .settings(
    name := "poems-hello",
    scalaVersion := "2.11.7",   
    unmanagedResourceDirectories in Compile <+= baseDirectory ( _ / "data" ),
    version := "1.0.0-SNAPSHOT"
  )

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-swing" % "2.11.0-M7"
)
