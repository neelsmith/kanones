package edu.holycross.shot.kanones.builder

import better.files._
import better.files.File._
import better.files.Dsl._
import java.io.{File => JFile}


/** Object for converting tabular source to FST statements.
*/
object VerbalAdjectiveRulesInstaller {

  /** Write FST rules for all verbal adjective data in a directory
  * of tabular files.
  *
  * @param srcDir Directory with inflectional rules.
  * @param targetFile File to write FST statements to.
  */
  def apply(srcDir: File, targetFile: File): Unit = {
    val vadjFst = fstForVerbalAdjectiveRules(srcDir)
      if(vadjFst.nonEmpty) {
        targetFile.overwrite(vadjFst)
      } else {}
  }


  /** Compose FST statements for all tables of
  * verbal adjective data found in a directory.
  *
  * @param srcDir Directory with lexical tables.
  */
  def fstForVerbalAdjectiveRules(srcDir: File) : String = {

    val vadjFiles = srcDir.glob("*.cex").toVector
    val rules = vadjFiles.flatMap(f =>
      f.lines.toVector.filter(_.nonEmpty).drop(1))
    val fst = verbalAdjectiveRulesToFst(rules.toVector)
    if (fst.nonEmpty) {
      "$vadjinfl$ = " + fst + "\n\n$vadjinfl$\n"
    } else {
      ""
    }
  }

  /** Compose FST for a single delimited-text line of a lexical
  * data table.
  *
  * @param line Line of data.
  */
  def verbalAdjectiveRuleToFst(line: String) : String = {
    val cols = line.split("#")
    //"lverbinfl.are_ptcpl1#conj1#ans#masc#nom#sg#pres#act"
    if (cols.size < 7) {
      println(s"Wrong number of columns ${cols.size}.\nCould not parse data line:\n${line}")
      throw new Exception(s"Wrong number of columns ${cols.size}.\nCould not parse data line:\n s${line}")
    } else {

      val fst = StringBuilder.newBuilder
      val ruleUrn = cols(0).replaceAll("_","\\\\_").
        replaceAll("\\.","\\\\.")
      val inflClass = cols(1).replaceAll("_","\\_")
      val inflString = cols(2)
      val grammGender = cols(3)
      val grammCase = cols(4)
      val grammNumber = cols(5)
      val vadj = cols(6)


      fst.append(s" <${inflClass}><${vadj}>${inflString}<${grammGender}><${grammCase}><${grammNumber}><u>${ruleUrn}</u>").toString
    }
  }

  /** Compose a single FST string for a Vector of rules
  * stated as one line of delimited-text each.
  *
  * @param data Vector of rules strings.
  */
  def verbalAdjectiveRulesToFst(data: Vector[String]) : String = {
    data.map(verbalAdjectiveRuleToFst(_)).mkString(" |\\\n")
  }

}
