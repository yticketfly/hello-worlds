lazy val root = (project in file(".")).
  settings(
    name := "lucene",
    version := "0.0.1",
    scalaVersion := "2.10.5"
  )

libraryDependencies += "org.apache.lucene" % "lucene-core" % "5.2.1"

libraryDependencies += "org.apache.lucene" % "lucene-analyzers-common" % "5.2.1"

libraryDependencies += "org.apache.lucene" % "lucene-queryparser" % "5.2.1"

libraryDependencies += "org.apache.lucene" % "lucene-spatial" % "5.2.1"

