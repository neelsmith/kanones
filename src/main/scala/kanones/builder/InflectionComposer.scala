package edu.holycross.shot.kanones.builder


import better.files._
import better.files.File._
import better.files.Dsl._

/** Factory object for composing and writing to a file the top-level
* transducer defining inflectional rules, inflection.fst, in the root of
* the parser build directory.
* Rules must already be installed in the corpus' inflection
* directory before using this!
*/
object InflectionComposer {

  val header = """
%% inflection.fst
% A transducer accepting all inflectional patterns.
%

$ending$ = """



  /**  Collects .fst files in a given directory and
  * composes fst identifiers for corresponding .a files.
  *
  * @param dir Directory with .fst files.
  */
  def inflectionFsts(dir: File): Vector[String] = {
    val files = dir.glob("*infl.fst").toVector
    val fst = files.map(f => {
      if (f.isEmpty) {
        ""
      } else {
        "\"<" + f.toString().replaceFirst(".fst$", ".a") + ">\""
      }
    })
    fst.filter(_.nonEmpty)
  }


  /**  Compose file "inflection.fst" in parser root, which simply
  * aggregates all .a files from the subdirectory "inflection".
  *
  * @param projectDir Working directory for a corpus parser,
  * e.g., REPO/parsers/CORPUS.
  */
  def apply(projectDir: File) : Unit = {
    val inflDir = projectDir/"inflection"
    if (! inflDir.exists()) {
      throw new Exception("Morphological rule set has not yet been installed!  No directory" + inflDir)
    } else  {
      val inflFiles = inflectionFsts(inflDir)
      val fstText = StringBuilder.newBuilder
      fstText.append(header)
      fstText.append( inflFiles.mkString(" |\\\n"))
      fstText.append ("\n\n$ending$\n")
      val finalText = fstText.toString

      val fstFile = projectDir/"inflection.fst"
      fstFile.overwrite(finalText)
    }
  }

}
