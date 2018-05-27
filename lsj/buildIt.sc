import scala.io.Source
import java.io.File
import scala.xml.XML
import edu.holycross.shot.xmlutils._
import java.io.PrintWriter
import edu.holycross.shot.greek._

val sourceDir =  new File("xml-by-letter/")


/** Collect plain text, but convert beta Greek to ucode.
*/
def greekFromXml(n: xml.Node, s: String = "", inGreek: Boolean = false) : String = {
  var buff = StringBuilder.newBuilder
  buff.append(s)
  n match {
    case t: xml.Text => {
      if (inGreek) {
        buff.append(LiteraryGreekString(t.text).ucode)
      } else {
        buff.append(t.text)
      }
    }

    case e: xml.Elem => {
      val v = e.attributes.toVector
      val gk =  v.map(_.value.text).filter(_ == "greek")
      //println(s"${e}:  greek? " + gk)
      for (ch <- e.child) {
        buff = new StringBuilder(greekFromXml(ch, buff.toString, gk.nonEmpty))
      }
    }
  }
  buff.toString
}

def xmlFileToGreekCex(f: File):  String = {
  val xmlString = scala.io.Source.fromFile(f).getLines.mkString("\n")
  val root = XML.loadString(xmlString)
  greekFromXml(root)
}


def xmlDirToGreekCex(dir: File) = {
  val filesList = dir.listFiles.filter(_.isFile).toVector.filter(_.toString.contains(".xml"))
  for (f <- filesList) {
      val outFile = f.toString.replaceFirst(".xml", ".cex")
      //println("Parse " + f + " and write to " + outFile)
      println("Preparing " +  outFile + "...")
      val xmlString = scala.io.Source.fromFile(f).getLines.mkString("\n")

      val root = XML.loadString(xmlString)

      val entries  = root \\ "entryFree"
      val entryStrings = for (e <- entries) yield {
        val idNode = e \ "@id"
        val idVal = idNode(0).text

        val bareText = greekFromXml(e)

        s"${idVal}#${bareText}"
      }
      new PrintWriter(outFile){write(entryStrings.mkString("\n")); close;}
  }
}

/*
def collectText(n: xml.Node, s: String): String = {
    var buff = StringBuilder.newBuilder
    buff.append(s)
    n match {
      case t: xml.Text => {
        buff.append(t.text)
      }

      case e: xml.Elem => {
        for (ch <- e.child) {
          buff = new StringBuilder(collectText(ch, buff.toString))
        }
      }
    }
    buff.toString
  }

*/

/** Find xml files in dir, and
* convert to simple CEX.
*
* @param dir Directory to look in for XML files.
*/

def xmlToAsciiCex(dir: File) = {
  val filesList = dir.listFiles.filter(_.isFile).toVector.filter(_.toString.contains(".xml"))
  for (f <- filesList) {
    val outFile = f.toString.replaceFirst(".xml", "-ascii.cex")

    //println("Parse " + f + " and write to " + outFile)

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
