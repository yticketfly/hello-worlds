package controllers

import play.api._
import play.api.mvc._
import play.api.Play.current

object Application2 extends Controller {

  def index = Action {
    val ff1 = Play.resourceAsStream("data1.txt").get
    val data1 = scala.io.Source.fromInputStream(ff1).getLines.toList

    val ff2 = Play.resourceAsStream("data2.txt").get
    val data2 = scala.io.Source.fromInputStream(ff2).getLines.toList
    Ok(data2.head.toString)
//    Ok(views.html.index("Your new application is ready."))
    // Redirect(routes.Products.list())
  }

}