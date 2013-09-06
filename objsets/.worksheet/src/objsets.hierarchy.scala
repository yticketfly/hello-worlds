package objsets

object hierarchy {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(79); 
  println("Welcome to the Scala worksheet");$skip(42); 
  val ll = List("aaa","bbb","ccc","abcd");System.out.println("""ll  : List[String] = """ + $show(ll ));$skip(25); 
  val str = "I have aaa";System.out.println("""str  : String = """ + $show(str ));$skip(29); val res$0 = 
  ll.exists(str.contains(_));System.out.println("""res0: Boolean = """ + $show(res$0));$skip(59); 
  //TweetReader.allTweets
  val t1 = new Tweet("a","aa",1);System.out.println("""t1  : objsets.Tweet = """ + $show(t1 ));$skip(33); 
  val t2 = new Tweet("b","bb",2);System.out.println("""t2  : objsets.Tweet = """ + $show(t2 ));$skip(33); 
  val t3 = new Tweet("c","cc",3);System.out.println("""t3  : objsets.Tweet = """ + $show(t3 ));$skip(33); 
  val t4 = new Tweet("d","dd",4);System.out.println("""t4  : objsets.Tweet = """ + $show(t4 ));$skip(48); 
  
  val tset12 = (new Empty).incl(t1).incl(t2);System.out.println("""tset12  : objsets.TweetSet = """ + $show(tset12 ));$skip(44); 
  val tset34= (new Empty).incl(t3).incl(t4);System.out.println("""tset34  : objsets.TweetSet = """ + $show(tset34 ));$skip(96); 
  //tset.incl(t1)
  //tset.incl(t2)
  //tset2.incl(t4)
    
  val tunion = tset12.union(tset34);System.out.println("""tunion  : objsets.TweetSet = """ + $show(tunion ));$skip(37); val res$1 = 
  tunion.filter(t => t.retweets > 2);System.out.println("""res1: objsets.TweetSet = """ + $show(res$1))}
                                  
  }
  
 
 

 