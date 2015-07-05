package com.ervii.knight.database

import java.sql.DriverManager

/**
 * http://postgis.refractions.net/documentation/javadoc/org/postgis/DriverWrapper.html
 * https://jdbc.postgresql.org/documentation/93/connect.html
 * http://www.enterprisedb.com/products-services-training/pgdownload
 * http://postgis.net/docs/manual-1.4/ch05.html#id424349
 */

object Tractor extends App {
  val car = CartmanConfig()
  Class.forName(car.jdbcDriver)
  val conn = DriverManager.getConnection(car.jdbcUrl, car.username, car.password)

  val s = conn.createStatement()
  val rs = s.executeQuery("select * from zebra")
  //http://stackoverflow.com/questions/2102662/scala-exposing-a-jdbc-resultset-through-a-generator-iterable
  //http://stackoverflow.com/questions/4927260/filling-a-scala-immutable-map-from-a-database-table
  val ss = Iterator.continually((rs.next(), rs)).takeWhile(_._1).map(_._2).toStream
  //  while( r.next() ) {
//    val id = r.getInt("id")
//    val name = r.getString("name")
//    println("Row " + id.toString + ":" + name)
//  }
  ss foreach { rr =>
    val id = rr.getInt("id")
    val name = rr.getString("name")
    val ll = rr.getString("latlon")
    println("Stream: " + id.toString + ":" + name)
  }
  s.close()
  conn.close()

  println("DUDO, getting money yo!")
}


case class CartmanConfig(dbname: String = "themepark",
                         host: String = "192.168.33.18",
                         port: Int = 5432,
                         username: String = "cartman",
                         password: String = "Respect-My-Authoritah"
                          ) {
  private val jdbcBase = "jdbc:postgresql_postGIS"
  val jdbcUrl = s"""${jdbcBase}://${host}:${port}/${dbname}"""
  val jdbcDriver = "org.postgis.DriverWrapper"
}