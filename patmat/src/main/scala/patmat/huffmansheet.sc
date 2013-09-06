package patmat

object huffmansheet {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  val st = Map('A' -> 3)                          //> st  : scala.collection.immutable.Map[Char,Int] = Map(A -> 3)
  val s4 = st + ('B' -> 4)                        //> s4  : scala.collection.immutable.Map[Char,Int] = Map(A -> 3, B -> 4)
  val s5 = st + ('B' -> 5)                        //> s5  : scala.collection.immutable.Map[Char,Int] = Map(A -> 3, B -> 5)
  val ll = List(1)                                //> ll  : List[Int] = List(1)
  ll match {
    case List(x) => println("list(1)")
    case x::Nil => println("only one")
  }                                               //> list(1)
  st('A')                                         //> res0: Int = 3
  val emp = List(('1',2),('2',3))                 //> emp  : List[(Char, Int)] = List((1,2), (2,3))
  val vil = emp.filter(_._1 == '1') match {case x :: tail => x; case Nil => Nil}
                                                  //> vil  : Product with Serializable = (1,2)
}