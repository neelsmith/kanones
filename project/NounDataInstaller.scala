import sbt._
import scala.io.Source
import java.io.PrintWriter


object NounDataInstaller {



  /** Creates FST file for each CEX file of
  * noun stems.
  *
  * @param srcDir Project directory.
  */
  def apply(repo: File, corpus: String) = {
    val lexDirectory = DataInstaller.madeDir(repo / s"parsers/${corpus}/lexica")

    val nounsDir = repo / s"datasets/${corpus}/stems-tables/nouns"
    val nounsOpt = (nounsDir) ** "*cex"
    val nounsFiles = nounsOpt.get
    println("\tbuilding noun stems from " + nounsDir)
    for (f <- nounsFiles) {
      val lexName = "lex-nouns-"+ f.getName().replaceFirst(".cex$", ".fst")
      val fstFile = lexDirectory /  lexName

      // omit empty lines and header
      val dataLines = Source.fromFile(f).getLines.toVector.filter(_.nonEmpty).drop(1)
      val fstLines = NounDataInstaller.nounLinesToFst(dataLines)

      new PrintWriter(fstFile) { write(fstLines); close }
    }
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
      val inflClass = cols(1).replaceAll("_","\\_").replaceAll("\\.","\\\\.")
      val inflString = DataInstaller.toFstAlphabet(cols(2))
      val grammGender = cols(3)
      val grammCase = cols(4)
      val grammNumber = cols(5)

      fstBuilder.append(s"<u>${ruleUrn}</u><u>${inflClass}</u>${inflString}<noun><${grammGender}><${grammCase}><${grammNumber}>")
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
    data.map(nounLineToFst(_)).mkString("\n") + "\n"
  }





}
