package ervii.farm.ocean.lib.querying

import ervii.farm.ocean.lib.indexing.BarnJammer
import org.apache.lucene.queryparser.classic.QueryParser
import org.apache.lucene.search.TopScoreDocCollector

trait Resulter {
  val results: Seq[String]
}

object PlaceResulter extends Resulter {


  val querystr = "lucene"
  val q = new QueryParser("title", BarnJammer.analyzer).parse(querystr)

  val hitsPerPage = 10
  val collector = TopScoreDocCollector.create(hitsPerPage)
  BarnJammer.searcher.search(q, collector)
  val hits = collector.topDocs().scoreDocs

  override val results = hits.toSeq map { h =>
    val docId = h.doc
    val value = BarnJammer.searcher.doc(docId)
    value.get("isbn")
  }

}