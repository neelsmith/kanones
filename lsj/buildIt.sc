import scala.io.Source
import java.io.File
import scala.xml.XML
import edu.holycross.shot.xmlutils._
import java.io.PrintWriter


val sourceDir =  new File("xml-by-letter/")

/** Find xml files in dir, and
* convert to simple CEX.
*
* @param dir Directory to look in for XML files.
*/
def xmlToCex(dir: File) = {
  val filesList = dir.listFiles.filter(_.isFile).toVector.filter(_.toString.contains(".xml"))
  for (f <- filesList) {
    val outFile = f.toString.replaceFirst(".xml", ".cex")

    println("Parse " + f + " and write to " + outFile)

    val xmlString = scala.io.Source.fromFile(f).getLines.mkString("\n")

    val root = XML.loadString(xmlString)

    val entries  = root \\ "entryFree"
    val entryStrings = for (e <- entries) yield {
      val idNode = e \ "@id"
      val idVal = idNode(0).text
      val bareText = TextReader.collectText(e)
      val stripped = bareText.replaceAll("#", "[£]")
      s"${idVal}#${stripped}"
    }
    new PrintWriter(outFile){write(entryStrings.mkString("\n")); close;} 
  }
}
