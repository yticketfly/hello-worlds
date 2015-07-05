//package models
//
//import play.api.Play.current
//import play.api.db.DB
//import anorm.SQL
//import anorm.SqlQuery
//class DBExamples {
//  val sql: SqlQuery = SQL("select * from products order by name asc")
//
//  def getAll: List[Product] = DB.withConnection {
//    implicit connection =>
//      sql().map(row =>
//        Product(row[Long]("id"), row[Long]("ean"),
//          row[String]("name"), row[String]("description"))
//      ).toList
//  }
//}
//
//class DBMatchingExample {
//  val sql: SqlQuery = SQL("select * from products order by name asc")
//
//  def getAllWithPatterns: List[Product] = DB.withConnection {
//    implicit connection =>
//      import anorm.Row
//      sql().collect {
//        case Row(Some(id: Long), Some(ean: Long), Some(name: String), Some(description: String)) =>
//          Product(id, ean, name, description)
//      }.toList
//  }
//}
//
//class DBParserExample {
//  val sql: SqlQuery = SQL("select * from products order by name asc")
//
//  import anorm.RowParser
//  val productParser: RowParser[Product] = {
//    import anorm.~
//    import anorm.SqlParser._
//    long("id") ~ long("ean") ~ str("name") ~ str("description") map { case id ~ ean ~ name ~ description => Product(id, ean, name, description) }
//  }
//
//  import anorm.ResultSetParser
//  val productsParser: ResultSetParser[List[Product]] = {
//    productParser *
//  }
//
//  def getAllWithParser: List[Product] = DB.withConnection {
//    implicit connection =>
//      sql.as(productsParser)
//  }
//
//  def productStockItemParser: RowParser[(Product, StockItem)] = {
//    import anorm.SqlParser._
//    import anorm.~
//    productParser ~ StockItem.stockItemParser map (flatten)
//  }
//
//  def getAllProductsWithStockItems: Map[Product, List[StockItem]] = {
//    DB.withConnection { implicit connection =>
//      val sql = SQL("select p.*, s.* " +
//        "from products p " +
//        "inner join stock_items s on (p.id = s.product_id)")
//      val results: List[(Product, StockItem)] =
//        sql.as(productStockItemParser *)
//      results.groupBy { _._1 }.mapValues { _.map { _._2 } }
//    }
//
//  }
//
//  def insert(product: Product): Boolean =
//    DB.withConnection { implicit connection =>
//      val addedRows = SQL("""insert into products values ({id}, {ean}, {name}, {description})""").on(
//        "id" -> product.id,
//        "ean" -> product.ean,
//        "name" -> product.name,
//        "description" -> product.description
//      ).executeUpdate()
//      addedRows == 1
//    }
//
//  def update(product: Product): Boolean =
//    DB.withConnection { implicit connection =>
//      val updatedRows = SQL("""update products set name = {name}, ean = {ean}, description = {description} where id = {id} """).on(
//        "id" -> product.id,
//        "name" -> product.name,
//        "ean" -> product.ean,
//        "description" -> product.description).
//        executeUpdate()
//      updatedRows == 1
//    }
//
//  def delete(product: Product): Boolean =
//    DB.withConnection { implicit connection =>
//      val updatedRows = SQL("delete from products where id = {id}").
//        on("id" -> product.id).executeUpdate()
//      updatedRows == 0
//    }
//
//}