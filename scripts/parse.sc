import sys.process._
import scala.language.postfixOps


val fst = "/usr/local/bin/fst-infl"


/**  Parse words listed in a file, and return their analyses
* as a String.
*
* @param wordsFile File with words to parse listed one per line.
* @param parser Name of corpus-specific parser, a subdirectory of
* kanones/parsers.
*/
def parse(wordsFile : String, parser: String = "analytical-types") : String = {
  def compiled = s"parsers/${parser}/greek.a"
  val cmd = fst + " " + compiled + "  " + wordsFile
  cmd !!
}
