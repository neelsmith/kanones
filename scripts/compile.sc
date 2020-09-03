// Define a function to compile a FST parser.

import edu.holycross.shot.kanones.builder._
import edu.holycross.shot.kanones._
import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._




import better.files._
import java.io.{File => JFile}
import better.files.Dsl._

import java.io.PrintWriter
import sys.process._
import scala.language.postfixOps


// Find out where FST tools are installed:
val macInstall = File("/usr/local/bin/")
val linuxInstall = File("/usr/bin/")

val compiler = if ( (macInstall / "fst-compiler-utf8").exists) {
  macInstall / "fst-compiler-utf8"
} else {
  linuxInstall / "fst-compiler-utf8"
}
val fstinfl = if ( (macInstall / "fst-infl").exists) {
  macInstall / "fst-infl"
} else {
  linuxInstall / "fst-infl"
}
val make = "/usr/bin/make"


// Expected directory layout of this repository:
val repo = "."
val datasetDir: File =  repo / "jvm/src/test/resources/datasets"
val parserDir = repo / "jvm/src/test/resources/parsers"
val fst = File("fst")
val datasetList = Vector("analytical_types")

// Compile a parser for a specified orthography.  The name should
// match a subdirectory of the datasets directory.
// The resulting parser will be written in the parsers directory.
def compile = {
  //val datasetList = Vector("shared", "shared-xls") :+ orthography
  val conf =  Configuration(compiler.toString, fstinfl.toString, make, datasetDir.toString)

  try {
    FstCompiler.compile(datasetDir, datasetList, parserDir, fst, conf)
    println("\nCompilation completed.\nParser is available in " +  parserDir + "/" + datasetList.mkString("-") + "/latin.a")
  } catch {
    case t: Throwable => println("Error trying to compile:\n" + t.toString)
  }
}

def usage: Unit = {
  println("\n\nBuild a parser:\n")
  println("\tcompile\n")
}

usage
