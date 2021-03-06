import com.github.bigtoast.sbtthrift.ThriftPlugin

lazy val root = (project in file(".")).
  settings(
    name := "kight",
    version := "0.0.1",
    scalaVersion := "2.10.4"
  )

Seq(ThriftPlugin.thriftSettings: _*)

libraryDependencies += "org.apache.thrift" % "libthrift" % "0.9.2"

libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.5.8"


resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies += "com.typesafe.akka" % "akka-actor_2.10" % "2.2-M1"