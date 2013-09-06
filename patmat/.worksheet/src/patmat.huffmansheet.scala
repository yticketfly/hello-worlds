package patmat

object huffmansheet {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(81); 
  println("Welcome to the Scala worksheet");$skip(25); 
  val st = Map('A' -> 3);System.out.println("""st  : scala.collection.immutable.Map[Char,Int] = """ + $show(st ));$skip(27); 
  val s4 = st + ('B' -> 4);System.out.println("""s4  : scala.collection.immutable.Map[Char,Int] = """ + $show(s4 ));$skip(27); 
  val s5 = st + ('B' -> 5);System.out.println("""s5  : scala.collection.immutable.Map[Char,Int] = """ + $show(s5 ));$skip(19); 
  val ll = List(1);System.out.println("""ll  : List[Int] = """ + $show(ll ));$skip(95); 
  ll match {
    case List(x) => println("list(1)")
    case x::Nil => println("only one")
  };$skip(10); val res$0 = 
  st('A');System.out.println("""res0: Int = """ + $show(res$0));$skip(34); 
  val emp = List(('1',2),('2',3));System.out.println("""emp  : List[(Char, Int)] = """ + $show(emp ));$skip(81); 
  val vil = emp.filter(_._1 == '1') match {case x :: tail => x; case Nil => Nil};System.out.println("""vil  : Product with Serializable = """ + $show(vil ))}
}
