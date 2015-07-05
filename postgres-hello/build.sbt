lazy val root = (project in file(".")).
  settings(
    name := "post-knight",
    version := "0.0.1",
    scalaVersion := "2.10.4"
  )

libraryDependencies += "org.postgresql" % "postgresql" % "9.3-1103-jdbc41"

libraryDependencies += "org.postgis" % "postgis-jdbc" % "1.3.3"
