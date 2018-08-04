import edu.holycross.shot.kanones.builder._

import sys.process._
import scala.language.postfixOps

import better.files._
import java.io.{File => JFile}
import better.files.Dsl._


val compiler = "/usr/local/bin/fst-compiler-utf8"
val fstinfl = "/usr/local/bin/fst-infl"
val make = "/usr/bin/make"


def compile(corpus: String, datasets: String = "datasets") = {
  val repo = File(".")

  val conf =  Configuration(compiler, fstinfl, make, datasets)
  println("CONFIGURATION IS " + conf)
  try {
    FstCompiler.compile(File(datasets), repo, corpus, conf, true)
    val kanonesParser = repo/"parsers"/corpus/"greek.a"

    println("\nCompilation completed.\nParser is available in " +  kanonesParser)
  } catch {
    case t: Throwable => println("Error trying to compile:\n" + t.toString)
  }

}



/**  Parse words listed in a file, and return their analyses
* as a String.
*
* @param wordsFile File with words to parse listed one per line.
* @param parser Name of corpus-specific parser, a subdirectory of
* kanones/parsers.
*/
def parse(wordsFile : String, parser: String = "analytical-types") : String = {
  def compiled = s"parsers/${parser}/greek.a"
  val cmd = fstinfl + " " + compiled + "  " + wordsFile
  cmd !!
}

println("\n\nCompile a parser:\n")
println("\tcompile(CORPUS, [DATASETS])\n")
println("Parse a word list:\n")
println("\tparse(WORDSFILE, [CORPUS])")
