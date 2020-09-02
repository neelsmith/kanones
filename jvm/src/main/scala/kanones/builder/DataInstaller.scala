package edu.holycross.shot.kanones.builder

import better.files._
import better.files.File._
import better.files.Dsl._


import wvlet.log._
import wvlet.log.LogFormatter.SourceCodeLogFormatter

/** Install stems data for a corpus-specific parser.
*
* @param dataSets Root directory for corpus-specific data sets.
* @param corpusList "Corpus names" are names subdirectories of the
* dataSets directory.  Each corpus can have stem tables to install.
* @param parsers Writable directory for work compiling parser. The parser
* source will be assembled in a sub-directory named from the list of names
* in corpusList.
*/
object DataInstaller extends LogSupport {

  def apply(dataSets: File, corpusList: Vector[String], parsers: File) : Unit = {

    info(s"Converting morphological tables in datasets contained in ${dataSets} to FST notation")


    /*
    val projectDir = DataInstaller.madeDir(repo / s"parsers/${corpus}")
    val lexDir = DataInstaller.madeDir(projectDir / "lexica")
    NounDataInstaller(repo, corpus)
    IndeclDataInstaller(repo, corpus)
    VerbDataInstaller(repo, corpus)
    */
  }


  // CONVERT TO BETTTERFILES
  // make sure directory exists
  /*
  def madeDir (dir: File) : File = {
    if (! dir.exists()) {
      dir.mkdir()
      dir
    } else {
      dir
    }
  }
  */

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
