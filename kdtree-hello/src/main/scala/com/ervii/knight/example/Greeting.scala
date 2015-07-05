package com.ervii.knight.example

import com.ervii.knight.geo.shape.{GeoShape, LatLon}
import com.spatial4j.core.distance.DistanceUtils
import com.vividsolutions.jts.geom.Envelope
import com.vividsolutions.jts.index.kdtree.{KdNode, KdTree}

import scala.collection.JavaConverters._

object Greeting extends App with GeoShape {

  val left = LatLon(-10, -10)
  val right = LatLon(10, 10)
  val env0 = new Envelope(left, right)
  println(env0)

  val p1 = LatLon(4,5)
  val p2 = LatLon(100,101)

  val t = new KdTree()

  println(t.isEmpty())
  val a1 = t.insert(p1, "closer point")
  println(a1)
  println(t.isEmpty())
  val a2 = t.insert(p2, "second point")
  println(a2)

  val pp = t.query(env0)
  println(pp)
  pp.asScala foreach {
    case a: KdNode => println(a.getData)
    case b => println("unknown type?")
  }

  import scala.io.Source
  val data = Source
    .fromInputStream(getClass.getResourceAsStream("DataLines.csv"))
    .getLines
    .toArray
    .map(_.split(","))
    .collect { case Array(x, y, v) => (LatLon(x.toDouble,y.toDouble) , v) }
//    .toMap
  data foreach { case (p, v) => t.insert(p, v)}

  val myPos = LatLon(38.137244, -122.235080)

  val searchEnv = quickEnvelope(myPos)

  println(searchEnv.toString)
  val foods = t.query(searchEnv)
  foods.asScala foreach {
    case a: KdNode => println(a.getData)
    case b => println("somewhere else?")
  }



  def degToKm( deg: Double) = {
     DistanceUtils.degrees2Dist(deg, DistanceUtils.EARTH_MEAN_RADIUS_KM)
  }

  def kmToDeg( km: Double) = {
     DistanceUtils.dist2Degrees(km, DistanceUtils.EARTH_MEAN_RADIUS_KM)
  }

  println(kmToDeg(0.100))

  println("Greetings location knight!")

}
