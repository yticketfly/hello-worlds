package com.ervii.knight.geo.poi

import com.ervii.knight.geo.shape.LatLon

case class Bathroom(override val name: String, override val center: LatLon, accessibility: Seq[Accessibility]) extends PlaceOfInterest {

}


sealed trait Accessibility

case object Man extends Accessibility
case object Woman extends Accessibility
case object Unisex extends Accessibility
case object Family extends Accessibility
case object Handicaped extends Accessibility

