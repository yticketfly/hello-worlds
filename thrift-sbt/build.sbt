import com.github.bigtoast.sbtthrift.ThriftPlugin

lazy val root = (project in file(".")).
  settings(
    name := "kight",
    version := "0.0.1",
    scalaVersion := "2.10.4"
  )

Seq(ThriftPlugin.thriftSettings: _*)

libraryDependencies += "org.apache.thrift" % "libthrift" % "0.9.2"
