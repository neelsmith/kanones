import sbt._
import scala.io.Source
import java.io.PrintWriter

object RulesInstaller {

  def apply(repo: File, corpus: String): Unit = {
  //def cexToFst(srcDir: File): Unit = {
    println(s"\nConvert inflectional rules tables in ${repo} to FST")
    buildRules(repo, corpus)

  }

  def buildRules(srcDir: File, corpus: String): Unit = {
    val inflDir = madeDir(srcDir / s"parsers/${corpus}/inflection")


    val nounFst = fstForNounRules(srcDir / s"datasets/${corpus}")
    val nounFstFile = inflDir / "nouninfl.fst"
    new PrintWriter(nounFstFile) { write(nounFst ); close }


    
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


  def fstForNounRules(srcDir: File) : String = {
    val nounsDir = srcDir / "rules-tables/nouns"
    val nounsOpt = (nounsDir) ** "*cex"
    val nounsFiles = nounsOpt.get
    println("\tbuilding inflection rules for nouns from " + nounsDir)

    val rules = nounsFiles.flatMap(f => Source.fromFile(f).getLines.toVector.filter(_.nonEmpty).drop(1))
    val fst = RulesInstaller.nounRulesToFst(rules.toVector)
    "$nouninfl$ = " + fst + "\n\n$nouninfl$\n"
  }

  def nounRuleToFst(line: String) : String = {
    val cols = line.split("#")
    if (cols.size < 6) {
      println("Wrong number of columns ${cols.size}.\nCould not parse data line:\n s${line}")
      throw new Exception(s"Wrong number of columns ${cols.size}.\nCould not parse data line:\n s${line}")
    } else {

      val fst = StringBuilder.newBuilder
      val ruleUrn = cols(0).replaceAll("_","\\\\_").
        replaceAll("\\.","\\\\.")
      val inflClass = cols(1).replaceAll("_","\\_")
      val inflString = toFstAlphabet(cols(2))
      val grammGender = cols(3)
      val grammCase = cols(4)
      val grammNumber = cols(5)

      fst.append(s" <${inflClass}><noun>${inflString}<${grammGender}><${grammCase}><${grammNumber}> <u>${ruleUrn}</u>").toString
    }
  }


  def nounRulesToFst(data: Vector[String]) : String = {
    data.map(nounRuleToFst(_)).mkString(" |\\\n")
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
