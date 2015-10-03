//package models
//
//import org.squeryl.adapters.H2Adapter
//import org.squeryl.{Session, SessionFactory}
//import play.api.db.DB
//import play.api.{Application, GlobalSettings}
//
//object SquerylStart extends GlobalSettings {
//    override def onStart(app: Application): Unit = {
//      SessionFactory.concreteFactory = Some(() =>
//        Session.create(DB.getConnection()(app), new H2Adapter) )
//    }
//}
