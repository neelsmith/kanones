package edu.holycross.shot.kanones.builder

import better.files._
import better.files.File._
import better.files.Dsl._
import java.io.{File => JFile}


/** Object for converting tabular source to FST statements.
*/
object NounRulesInstaller {

  /** Write FST rules for all noun data in a directory
  * of tabular files.
  *
  * @param srcDir Directory with inflectional rules.
  * @param targetFile File to write FST statements to.
  */
  def apply(srcDir: File, targetFile: File): Unit = {
    println("\n\nINSTALL NOUN RULES\n\n")
    val nounFst = fstForNounRules(srcDir)
    if(nounFst.nonEmpty) {
      println("WRITTInG DATA TO " + targetFile)
      targetFile.overwrite(nounFst)
      println("Exists? " + targetFile.exists())
    } else {
      println("NO NOUN DATA")
    }
  }


  /** Compose FST statements for all tables of
  * noun data found in a directory.
  *
  * @param srcDir Directory with lexical tables.
  */
  def fstForNounRules(srcDir: File) : String = {
    println("NOUNDS FROM " + srcDir)
    val nounsFiles = srcDir.glob("*.cex").toVector
    val rules = nounsFiles.flatMap(f =>
      f.lines.toVector.filter(_.nonEmpty).drop(1))
    val fst = nounRulesToFst(rules.toVector)
    println("YIELDED #"+ fst + "#")
    if (fst.nonEmpty) {
      "$nouninfl$ = " + fst + "\n\n$nouninfl$\n"
    } else {
      ""
    }
  }

  /** Compose FST for a single delimited-text line of a lexical
  * data table.
  *
  * @param line Line of data.
  */
  def nounRuleToFst(line: String) : String = {
    val cols = line.split("#")
    //smyth\\.n47039_0#
    //lexent\\.n47039#
    //ni_k#fem#h_hs#stemultacc
    if (cols.size < 6) {
      println(s"Wrong number of columns ${cols.size}.\nCould not parse data line:\n${line}")
      throw new Exception(s"Wrong number of columns ${cols.size}.\nCould not parse data line:\n s${line}")
    } else {

      val fst = StringBuilder.newBuilder
      val ruleUrn = cols(0).replaceAll("_","\\\\_").
        replaceAll("\\.","\\\\.")

      val inflClass = cols(1).replaceAll("_","\\\\_")
      val inflString = cols(2) // DataInstaller.toFstAlphabet(cols(2))
      val grammGender = cols(3)
      val grammCase = cols(4)
      val grammNumber = cols(5)

      fst.append(s" <${inflClass}><noun>${inflString}<${grammGender}><${grammCase}><${grammNumber}><u>${ruleUrn}</u>").toString
    }
  }

  /** Compose a single FST string for a Vector of rules
  * stated as one line of delimited-text each.
  *
  * @param data Vector of rules strings.
  */
  def nounRulesToFst(data: Vector[String]) : String = {
    data.map(nounRuleToFst(_)).mkString(" |\\\n")
  }

}
