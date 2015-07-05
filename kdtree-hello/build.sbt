lazy val root = (project in file(".")).
  settings(
    name := "knight",
    version := "0.0.1",
    scalaVersion := "2.10.4"
  )

libraryDependencies += "com.spatial4j" % "spatial4j" % "0.4.1"

libraryDependencies += "com.vividsolutions" % "jts" % "1.13"
