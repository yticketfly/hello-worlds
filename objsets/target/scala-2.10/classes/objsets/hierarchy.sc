package objsets

object hierarchy {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  val ll = List("aaa","bbb","ccc","abcd")         //> ll  : List[String] = List(aaa, bbb, ccc, abcd)
  val str = "I have aaa"                          //> str  : String = I have aaa
  ll.exists(str.contains(_))                      //> res0: Boolean = true
  //TweetReader.allTweets
  val t1 = new Tweet("a","aa",1)                  //> t1  : objsets.Tweet = User: a
                                                  //| Text: aa [1]
  val t2 = new Tweet("b","bb",2)                  //> t2  : objsets.Tweet = User: b
                                                  //| Text: bb [2]
  val t3 = new Tweet("c","cc",3)                  //> t3  : objsets.Tweet = User: c
                                                  //| Text: cc [3]
  val t4 = new Tweet("d","dd",4)                  //> t4  : objsets.Tweet = User: d
                                                  //| Text: dd [4]
  
  val tset12 = (new Empty).incl(t1).incl(t2)      //> tset12  : objsets.TweetSet = {.User: a
                                                  //| Text: aa [1]{.User: b
                                                  //| Text: bb [2].}}
  val tset34= (new Empty).incl(t3).incl(t4)       //> tset34  : objsets.TweetSet = {.User: c
                                                  //| Text: cc [3]{.User: d
                                                  //| Text: dd [4].}}
  //tset.incl(t1)
  //tset.incl(t2)
  //tset2.incl(t4)
    
  val tunion = tset12.union(tset34)               //> tunion  : objsets.TweetSet = {{{.User: a
                                                  //| Text: aa [1].}User: b
                                                  //| Text: bb [2].}User: c
                                                  //| Text: cc [3]{.User: d
                                                  //| Text: dd [4].}}
  tunion.filter(t => t.retweets > 2)              //> res1: objsets.TweetSet = {{.User: c
                                                  //| Text: cc [3].}User: d
                                                  //| Text: dd [4].}
                                  
  }
  
 
 

 