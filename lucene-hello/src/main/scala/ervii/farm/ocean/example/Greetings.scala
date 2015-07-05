package ervii.farm.ocean.example

import java.io.File

import com.spatial4j.core.context.SpatialContext
import com.spatial4j.core.distance.DistanceUtils
import com.spatial4j.core.shape.{Point, Shape}
import ervii.farm.ocean.lib.querying.PlaceResulter
import org.apache.lucene.analysis.standard.StandardAnalyzer
import org.apache.lucene.document.Field.Store
import org.apache.lucene.document._
import org.apache.lucene.index._
import org.apache.lucene.search.{ScoreDoc, MatchAllDocsQuery, Sort, IndexSearcher}
import org.apache.lucene.spatial.prefix.RecursivePrefixTreeStrategy
import org.apache.lucene.spatial.prefix.tree.GeohashPrefixTree
import org.apache.lucene.spatial.query.SpatialArgs
import org.apache.lucene.store.{RAMDirectory, SimpleFSDirectory}

object Greetings extends App {


//  PlaceResulter.results foreach { h =>
//    println("isbn:" + h)
//  }

  //http://mad4search.blogspot.in/2013/06/implementing-geospatial-search-using.html

  val indexPath = "/tmp/geo_spatial_index"

  val a = new StandardAnalyzer()
  val iwc = new IndexWriterConfig(a)

  val directory = new RAMDirectory()

  val indexWriter = new IndexWriter(directory, iwc)

  val ctx = SpatialContext.GEO

  val grid = new GeohashPrefixTree(ctx, 11)

  val strategy = new RecursivePrefixTreeStrategy(grid, "location")


  def newGeoDocument(id: Int, name: String, shape: Shape) = {

    val ft = new FieldType()
    ft.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS)
    ft.setStored(true)

    val doc = new Document()

    doc.add(new IntField("id", id, Store.YES))
    doc.add(new Field("name", name, ft))
    strategy.createIndexableFields(shape) foreach {
      f => doc.add(f)
    }

    doc.add(new StoredField(strategy.getFieldName, ctx.toString(shape)));

    doc
  }

  indexWriter.addDocument(newGeoDocument(1, "Bangalore", ctx.makePoint(12.9558, 77.620979)))
  indexWriter.addDocument(newGeoDocument(2, "Cubbon Park", ctx.makePoint(12.974045, 77.591995)))
  indexWriter.addDocument(newGeoDocument(3, "Tipu palace", ctx.makePoint(12.959365, 77.573792)))
  indexWriter.addDocument(newGeoDocument(4, "Bangalore palace", ctx.makePoint(12.998095, 77.592041)))
  indexWriter.addDocument(newGeoDocument(5, "Monkey Bar", ctx.makePoint(12.97018, 77.61219)))
  indexWriter.addDocument(newGeoDocument(6, "Cafe Cofee day", ctx.makePoint(12.992189, 80.2348618)))

  indexWriter.commit()
  indexWriter.close()


  val indexReader = DirectoryReader.open(new SimpleFSDirectory(new File(indexPath).toPath))
  val searcher = new IndexSearcher(indexReader)


  def search(lat: Double, lng: Double, distance: Int) {

    val p = ctx.makePoint(lat, lng)

    import org.apache.lucene.spatial.query.SpatialOperation

    val args = new SpatialArgs(SpatialOperation.Intersects, ctx.makeCircle(lat, lng, DistanceUtils.dist2Degrees(distance, DistanceUtils.EARTH_MEAN_RADIUS_KM)))
    val filter = strategy.makeFilter(args)

    val valueSource = strategy.makeDistanceValueSource(p)
    val distSort = new Sort(valueSource.getSortField(false)).rewrite(searcher)

    val limit = 10
    val topDocs = searcher.search(new MatchAllDocsQuery(), filter, limit, distSort)
    val scoreDocs = topDocs.scoreDocs

    scoreDocs foreach { s: ScoreDoc =>

      val doc = searcher.doc(s.doc)
      val docPoint = ctx.readShape(doc.get(strategy.getFieldName)).asInstanceOf[Point]
      val docDistDEG = ctx.getDistCalc.distance(args.getShape.getCenter, docPoint)
      val docDistInKM = DistanceUtils.degrees2Dist(docDistDEG, DistanceUtils.EARTH_EQUATORIAL_RADIUS_KM)
      System.out.println(doc.get("id") + "\t" + doc.get("name") + "\t" + docDistInKM + " km ")

    }
  }

  search(12.974045,77.591995, 4)

    println("greetings, lucene")
}
