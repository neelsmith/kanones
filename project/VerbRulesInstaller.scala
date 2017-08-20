import sbt._
import scala.io.Source
import java.io.PrintWriter


/** Object for converting tabular source to FST statements.
*/
object VerbRulesInstaller {

  /** Write FST rules for all verb data in a directory
  * of tabular files.
  *
  * @param srcDir Directory with inflectional rules.
  * @param targetFile File to write FST statements to.
  */
  def apply(srcDir: File, targetFile: File): Unit = {
    val verbFst = fstForVerbRules(srcDir)
    new PrintWriter(targetFile) { write(verbFst ); close }
  }


  /** Compose FST statements for all tables of
  * verb data found in a directory.
  *
  * @param srcDir Directory with lexical tables.
  */
  def fstForVerbRules(srcDir: File) : String = {
    val filesOpt = (srcDir) ** "*cex"
    val fileList = filesOpt.get
    println("\tbuilding inflection rules for verbs from " + srcDir)

    val rules = fileList.flatMap(f => Source.fromFile(f).getLines.toVector.filter(_.nonEmpty).drop(1))
    val fst = verbRulesToFst(rules.toVector)
    "$verbinfl$ = " + fst + "\n\n$verbinfl$\n"
  }

  /** Compose FST for a single delimited-text line of a lexical
  * data table.
  *
  * @param line Line of data.
  */
  def verbRuleToFst(line: String) : String = {
    val cols = line.split("#")

// model of source:
//
//RuleUrn#InflectionClasses#Ending#Person#Number#Tense#Mood#Voice
//verbinfl.w_pp3_aor_indic3b#w_pp3#e#3rd#sg#aor#indic#actj
//


    if (cols.size < 8) {
      println("Wrong number of columns ${cols.size}.\nCould not parse data line:\n s${line}")
      throw new Exception(s"Wrong number of columns ${cols.size}.\nCould not parse data line:\n s${line}")
    } else {



// #3rd
// #sg#aor#indic#actj
      val fst = StringBuilder.newBuilder
      val ruleUrn = cols(0).replaceAll("_","\\\\_").
        replaceAll("\\.","\\\\.")  //verbinfl.w_pp3_aor_indic3b
      val inflClass = cols(1).replaceAll("_","\\_")  //#w_pp3
      val inflString = DataInstaller.toFstAlphabet(cols(2)) // #e
      // PNTMV
      val pers = cols(3)
      val grammNumber = cols(4)
      val tense = cols(5)
      val mood = cols(6)
      val voice = cols(7)
      // model of FST:
      //  <aor2>
      //<verb>
      // e/tw
      //<3rd><sg><aor><imptv><act><u>verbinfl\.w\_pres\_imptv3</u>

      fst.append(s" <${inflClass}><verb>${inflString}<${pers}><${grammNumber}><${tense}><${mood}><${voice}> <u>${ruleUrn}</u>").toString
    }

  }

  /** Compose a single FST string for a Vector of rules
  * stated as one line of delimited-text each.
  *
  * @param data Vector of rules strings.
  */
  def verbRulesToFst(data: Vector[String]) : String = {
    data.map(verbRuleToFst(_)).mkString(" |\\\n")
  }

}
