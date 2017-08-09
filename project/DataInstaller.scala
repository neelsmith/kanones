import sbt._
import scala.io.Source
import java.io.PrintWriter


object DataInstaller {


  def apply(repo: File, corpus: String): Unit = {
    println(s"Convert morphological lexicon tables in ${repo} to FST")
    val projectDir = madeDir(repo / s"parsers/${corpus}")
    val lexDir = madeDir(projectDir / "lexica")
    buildNounStems(repo, corpus)
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


  /** Creates FST file for each CEX file of
  * noun stems.
  *
  * @param srcDir Project directory.
  */
  def buildNounStems(repo: File, corpus: String) = {
    val lexDirectory = madeDir(repo / s"parsers/${corpus}/lexica")

    val nounsDir = repo / s"datasets/${corpus}/stems-tables/nouns"
    val nounsOpt = (nounsDir) ** "*cex"
    val nounsFiles = nounsOpt.get
    println("\tbuilding nouns stems from " + nounsDir)

    for (f <- nounsFiles) {
      val fstFile = lexDirectory / f.getName().replaceFirst(".cex$", ".fst")
      // omit empty lines and header
      val dataLines = Source.fromFile(f).getLines.toVector.filter(_.nonEmpty).drop(1)
      val fstLines = DataInstaller.nounLinesToFst(dataLines)
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
      val inflString = toFstAlphabet(cols(2))
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
