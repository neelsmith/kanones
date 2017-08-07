import sbt._
import scala.io.Source
import java.io.PrintWriter
import Path.rebase

object DataConverter {

  /** Find all CEX data files in a given project,
  * and create corresponding FST files.
  *
  * @param srcDir Project directory.
  */
  def cexToFst(srcDir: File): Unit = {
    buildNounStems(srcDir)
  }

  /** Creates FST file for each CEX file of
  * noun stems.
  *
  * @param srcDir Project directory.
  */
  def buildNounStems(srcDir: File) = {
    val nounsDir = srcDir / "stems/nouns"
    val nounsOpt = (nounsDir) ** "*cex"
    val nounsFiles = nounsOpt.get
    println("\tbuilding nouns stems from " + nounsDir)
    // remap to "lexica" dir
    /*
    for (f <- nounsFiles) {
      val fstFile = nounsDir / f.getName().replaceFirst(".cex$", ".fst")
      // omit empty lines and header
      val dataLines = Source.fromFile(f).getLines.toVector.filter(_.nonEmpty).drop(1)
      val fstLines = DataConverter.nounLinesToFst(dataLines)
      new PrintWriter(fstFile) { write(fstLines); close }
    }
    */
  }

  /** Translates one line of CEX data documenting a noun stem
  * to a corresponding single line of FST.
  *
  * @param line CEX line to translate.
  */
  def nounLineToFst(line: String) : String = {
    val cols = line.split("#")
    // 7th "comments" column is optional
    if (cols.size < 6) {
      println("Wrong number of columns ${cols.size}.\nCould not parse data line:\n s${line}")
      throw new Exception(s"Wrong number of columns ${cols.size}.\nCould not parse data line:\n s${line}")
    } else {
      val fstBuilder = StringBuilder.newBuilder
      val ruleUrn = cols(0).replaceAll("_","\\\\_").replaceAll("\\.","\\\\.")
      val inflClass = cols(1).replaceAll("_","\\_")
      val inflString = toFstAlphabet(cols(2))
      val grammGender = cols(3)
      val grammCase = cols(4)
      val grammNumber = cols(5)

      fstBuilder.append(s" <${inflClass}><noun>${inflString}<${grammGender}><${grammCase}><${grammNumber}> <u>${ruleUrn}</u>")
      fstBuilder.toString
    }
  }

  /** Convert a Vector of noun stem data in CES form to
  * a single valid FST string.
  *
  * @param data Vector of strings in which each string
  * represents one noun stem in CEX form.
  */
  def nounLinesToFst(data: Vector[String]) : String = {
    data.map(nounLineToFst(_)).mkString("\n")
  }




  /** Rewrite characters in s that are not part of FST's
  * alphabet to corresponding FST representation.
  *
  * @param s String to rewrite.
  */
  def toFstAlphabet( s: String) = {
    s.
     replaceAll("_", "<lo>").
     replaceAll("\\^", "<sh>").
     replaceAll("\\(", "<ro>").
     replaceAll("\\)", "<sm>").
     replaceAll("=", "\\\\=").
     replaceAll("\\|", "<isub>")
   }



}
