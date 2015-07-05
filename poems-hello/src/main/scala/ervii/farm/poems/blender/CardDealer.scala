package ervii.farm.poems.blender

import scala.io.Source

object CardDealer extends App {
  println("Welcome to the poem garden!")
  println("欢迎进入古诗花园！")
  println()
  val rawLines = Source
    .fromInputStream(getClass.getResourceAsStream("elementary80.txt"))
    .getLines()
    .filterNot(_.startsWith("("))
    .filterNot("""^\d.*""".r.pattern.matcher(_).matches())
    .filterNot(_.isEmpty)
    .toSeq
    .tail
  val endings = Array('。', '！', '？', '?')
  val middlings = Array('，', ',' )

  val splitSentences: Seq[String] = rawLines flatMap { _.split(endings) }

  val splitPhrases: Seq[Seq[String]] = splitSentences map { _.split(middlings).toSeq }

  val prettyLines = splitPhrases map { p => "[" + (p map { "'" + _ + "'"} mkString(",")) + "]"} mkString(",\n")
println("[ " + prettyLines + " ]")
  val timestamp: Long = System.currentTimeMillis
  val randomGen = new scala.util.Random(timestamp)

  def nextLine() = {
    val lineNumber = randomGen.nextInt(splitPhrases.size)

    val pickedLine = splitPhrases(lineNumber)
    val posToMask = randomGen.nextInt(pickedLine.size)

    val maskedLine = pickedLine.updated(posToMask, "_ " * pickedLine(posToMask).length)

    maskedLine.mkString("\n")
  }
}
