package com.ervii.knight.geo.shape

import com.vividsolutions.jts.geom.{Envelope, Coordinate}

trait GeoShape {
  implicit def jtsCoordinateToLatLon(jtsPoint: Coordinate): LatLon = {
    new LatLon(jtsPoint.x, jtsPoint.y)
  }

  implicit def latLonToJtsCoordinate(latlon: LatLon): Coordinate = {
    new Coordinate(latlon.lat, latlon.lon)
  }

  val dLat = 0.000233
  val dLon = 0.000301

  def quickEnvelope(latlon: LatLon): Envelope = latlon match {
    case LatLon(x, y) =>
      val x1 = x + dLat
      val x2 = x - dLat
      val y1 = y + dLon
      val y2 = y - dLon
      new Envelope(x1, x2, y1, y2)
  }
}
