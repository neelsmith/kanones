import sbt._
import scala.io.Source
import java.io.PrintWriter


/** Object for converting tabular source to FST statements.
*/
object IndeclRulesInstaller {

  /** Write FST rules for all noun data in a directory
  * of tabular files.
  *
  * @param srcDir Directory with inflectional rules.
  * @param targetFile File to write FST statements to.
  */
  def apply(srcDir: File, targetFile: File): Unit = {
    val nounFst = fstForIndeclRules(srcDir)
    new PrintWriter(targetFile) { write(nounFst ); close }
  }


  /** Compose FST statements for all tables of
  *  data for indeclinable forms found in a directory.
  *
  * @param srcDir Directory with lexical tables.
  */
  def fstForIndeclRules(srcDir: File) : String = {
    val rulesOpt = (srcDir) ** "*cex"
    val rulesFiles = rulesOpt.get
    println("\tbuilding inflection rules for indeclinables from " + srcDir)

    val rules = rulesFiles.flatMap(f => Source.fromFile(f).getLines.toVector.filter(_.nonEmpty).drop(1))
    val fst = indeclRulesToFst(rules.toVector)
    "$indeclinfl$ = " + fst + "\n\n$indeclinfl$\n"
    }

  /** Compose FST for a single delimited-text line of a lexical
  * data table.
  *
  * @param line Line of data.
  */
  def indeclRuleToFst(line: String) : String = {
    val cols = line.split("#")
    if (cols.size < 2) {
      println("Wrong number of columns ${cols.size}.\nCould not parse data line:\n s${line}")
      throw new Exception(s"Wrong number of columns ${cols.size}.\nCould not parse data line:\n s${line}")
    } else {

      val fst = StringBuilder.newBuilder
      val ruleUrn = cols(0).replaceAll("_","\\\\_").
        replaceAll("\\.","\\\\.")
      val inflClass = cols(1).replaceAll("_","\\_")

      fst.append(s" <${inflClass}><indecl><u>${ruleUrn}</u>").toString
    }
  }

  /** Compose a single FST string for a Vector of rules
  * stated as one line of delimited-text each.
  *
  * @param data Vector of rules strings.
  */
  def indeclRulesToFst(data: Vector[String]) : String = {
    data.map(indeclRuleToFst(_)).mkString(" |\\\n")
  }

}
