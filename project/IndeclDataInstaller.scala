import sbt._
import scala.io.Source
import java.io.PrintWriter


object DataInstaller {


  def apply(repo: File, corpus: String) = {
    val lexDirectory = madeDir(repo / s"parsers/${corpus}/indeclinable")

    val dir = repo / s"datasets/${corpus}/stems-tables/indeclinable"
    val rulesOpt = (dir) ** "*cex"
    val rulesFiles = rulesOpt.get
    println("\tbuilding indeclinable stems from " + dir)

    for (f <- rulesFiles) {
      val fstFile = lexDirectory / f.getName().replaceFirst(".cex$", ".fst")
      // omit empty lines and header
      val dataLines = Source.fromFile(f).getLines.toVector.filter(_.nonEmpty).drop(1)
      val fstLines = DataInstaller.indeclLinesToFst(dataLines)
      println("INDECL LINES: \n" + fstLines)
      new PrintWriter(fstFile) { write(fstLines); close }
    }
  }


  def indeclLineToFst(line: String) : String = {
    val cols = line.split("#")
    // 7th "comments" column is optional
    if (cols.size < 4) {
      println("Wrong number of columns ${cols.size}.\nCould not parse data line:\n s${line}")
      throw new Exception(s"Wrong number of columns ${cols.size}.\nCould not parse data line:\n s${line}")
    } else {

      val fstBuilder = StringBuilder.newBuilder
      val ruleUrn = cols(0).replaceAll("_","\\\\_").replaceAll("\\.","\\\\.")
      val inflClass = cols(1).replaceAll("_","\\_").replaceAll("\\.","\\\\.")
      val inflString = toFstAlphabet(cols(2))
      val ptOfSpeech = cols(3)

      fstBuilder.append(s"<u>${ruleUrn}</u><u>${inflClass}</u>${inflString}<${ptOfSpeech}>")
      //<u>ruleUrn</u><u>LExENT</u>STRING<POS>
//<u>lysias.n4357_0</u><u>lexent.n4357</u>a<sm>lla/<conjunct>
      //fstBuilder.append(s"<u>${ruleUrn}</u><u>${inflClass}</u>${inflString}<noun><${grammGender}><${grammCase}><${grammNumber}>")
      fstBuilder.toString
    }
  }

  /** Convert a Vector of noun stem data in CES form to
  * a single valid FST string.
  *
  * @param data Vector of strings in which each string
  * represents one noun stem in CEX form.
  */
  def indeclLinesToFst(data: Vector[String]) : String = {
    data.map(indeclLineToFst(_)).mkString("\n") + "\n"
  }


  // make sure directory exists
  def madeDir (dir: File) : File = {
    if (! dir.exists()) {
      dir.mkdir()
      dir
    } else {
      dir
    }
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
