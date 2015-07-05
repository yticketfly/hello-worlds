package ervii.farm.ocean.lib.indexing

import org.apache.lucene.analysis.standard.StandardAnalyzer
import org.apache.lucene.document.{Document, Field, StringField, TextField}
import org.apache.lucene.index.{DirectoryReader, IndexWriter, IndexWriterConfig}
import org.apache.lucene.search.IndexSearcher
import org.apache.lucene.store.RAMDirectory

trait Jammer {
  val searcher: IndexSearcher
  val analyzer = new StandardAnalyzer()
}

object BarnJammer extends Jammer {
  val index = new RAMDirectory()

  val config = new IndexWriterConfig(this.analyzer)

  val w = new IndexWriter(index, config)
  val rawSeq = Seq(
    ("Lucene in Action", "193398817")
    ,("Lucene for Dummies", "55320055Z")
    ,("Managing Gigabytes", "55063554A")
    ,("The Art of Computer Science", "9900333X")
  )
  rawSeq foreach { case (name: String, id: String) =>
    val doc = new Document()
    doc.add(new StringField("isbn", id, Field.Store.YES))
    doc.add(new TextField("title", name, Field.Store.YES))
    w.addDocument(doc)
  }
  w.close()

  val reader = DirectoryReader.open(index)
  override val searcher = new IndexSearcher(reader)

}