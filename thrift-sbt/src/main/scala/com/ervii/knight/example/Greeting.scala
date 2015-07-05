package com.ervii.knight.example

import com.ervii.knight.primitives.thriftjava._

object Greeting extends App{

	val ll = new Latlon(37.773972, -122.431297)
	println("Greetings knight!" + ll.toString)
}
