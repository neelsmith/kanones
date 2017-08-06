import sbt._
import scala.io.Source
import java.io.PrintWriter

object DataConverter {


  def cexToFst(srcDir: File): Unit = {
    println(s"Convert CEX files in ${srcDir} to FST")
    buildNounStems(srcDir)
  }

  def buildNounStems(srcDir: File) = {
    val nounsDir = srcDir / "stems/nouns"
    val nounsOpt = (nounsDir) ** "*cex"
    val nounsFiles = nounsOpt.get
    println("\tBuilding nouns stems from " + nounsDir)

    for (f <- nounsFiles) {
      val fstFile = nounsDir / f.getName().replaceFirst(".cex$", ".fst")
      println ("convert " + f + " to " + fstFile)
      // omit empty lines and header
      val dataLines = Source.fromFile(f).getLines.toVector.filter(_.nonEmpty).drop(1)
      val fstLines = DataConverter.nounLinesToFst(dataLines)
      new PrintWriter(fstFile) { write(fstLines); close }
    }
  }

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
  def nounLinesToFst(data: Vector[String]) : String = {
    data.map(nounLineToFst(_)).mkString("\n")
  }





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
