package edu.holycross.shot.kanones.builder

import scala.sys.process._

import better.files.{File => ScalaFile, _}
import better.files.Dsl._


object FstCompiler {


  /*def installAlphabet(dataSrc: ScalaFile, repo: ScalaFile, corpus: String): Unit = {
    val symbolsDir = repo/"parsers"/corpus/"symbols"
    mkdirs(symbolsDir)
    (dataSrc/corpus/"orthography/alphabet.fst").copyTo(symbolsDir/"alphabet.fst")
  }*/

  def compileAll(dataDirectory: ScalaFile, baseDir: ScalaFile, corpus: String, conf: Configuration) : Unit = {
    //println("INSTALL ALPHABET")
    //BuildComposer.installAlphabet(dataDirectory, baseDir, corpus)
    //val alphaTarget = baseDir/"parsers"/corpus/"symbols/alphabet.fst"
    //require( alphaTarget.exists(), "NO ALPHABET FILE: "+alphaTarget)
    // Install data and rules, converting tabular data to FST
    //println(s"\n\n  Install data for ${corpus} in ${dataDirectory}...")
    DataInstaller(dataDirectory, baseDir, corpus)

    println(s"Install rules for ${corpus} in ${dataDirectory}...")
    RulesInstaller(dataDirectory, baseDir, corpus)

    //println("Compose build")
    // Compose makefiles and higher-order FST for build system
    BuildComposer(dataDirectory, baseDir, corpus, conf.fstcompile)

    // Build it!
    val buildDirectory = baseDir/"parsers"/corpus
    val inflMakefile = buildDirectory/"inflection/makefile"
    val makeInfl = s"${conf.make} -f ${inflMakefile}"
    makeInfl !

    val makefile = buildDirectory / "makefile"
    val doit = s"${conf.make} -f ${makefile}"
    doit !
  }


  /**  Compose a parser in FST from tabular source data, and compile it to binary form.
  *
  * @param dataDirectory
  * @param baseDir
  * @param corpus
  * @param conf
  */
  def compile(dataDirectory: ScalaFile, baseDir: ScalaFile, corpus: String, conf: Configuration, replaceExisting: Boolean = true) : Unit = {

    val projectDir = baseDir/"parsers"/corpus
    if (projectDir.exists) {
      replaceExisting match {
        case true => {
          projectDir.delete()
          compileAll(dataDirectory, baseDir, corpus, conf)
        }
        case false => {
          println("Directory " + projectDir + " exists, and setting to delete exising parser is false.")
          println("Cowardly refusing to continue.")
        }
      }
    } else {
      compileAll(dataDirectory, baseDir, corpus, conf)
    }
  }
}
