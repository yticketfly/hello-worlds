package com.ervii.knight.geo.poi
import com.ervii.knight.geo.shape.LatLon

abstract class PlaceOfInterest {
  def name: String

  def center: LatLon

  def id: Long = 1
}


