//package models
//
//
//case class Product(id: Long,
//                   ean: Long,
//                   name: String,
//                   description: String)
//
//case class Warehouse(id: Long, name: String)
//
//case class StockItem(id: Long,
//                     productId: Long,
//                     warehouseId: Long,
//                     quantity: Long)
//
//object StockItem {
//  import anorm.RowParser
//  val stockItemParser: RowParser[StockItem] = {
//    import anorm.SqlParser._
//    import anorm.~
//    long("id") ~ long("product_id") ~
//      long("warehouse_id") ~ long("quantity") map {
//      case id ~ productId ~ warehouseId ~ quantity =>
//        StockItem(id, productId, warehouseId, quantity)
//    }
//  }
//}