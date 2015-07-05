package com.ervii.knight.geo.poi

import com.ervii.knight.geo.shape.LatLon

case class Dinning(override val name: String, override val center: LatLon, entries: Seq[LatLon]) extends PlaceOfInterest {

}

