package edu.holycross.shot.kanones.builder

import better.files.{File => ScalaFile, _}
import better.files.Dsl._


/** Write the top-level transducer, greek.fst.
*/
object ParserComposer {

  /** Static file header. */
  val header = """
%% greek.fst : a Finite State Transducer for ancient greek morphology
%
% All symbols used in the FST:
"""

  /** Write greek.fst for parser in a given directory.
  *  This
  *
  * @param projectDir Directory where parser is to be written.
  */
  def apply(projectDir: ScalaFile) : Unit = {
    if (! projectDir.exists){throw new Exception("ParserComposer:  cannot compose parser FST for empty or non-existent directory.")}

    val lexica = projectDir/"lexica"
    if (! lexica.exists){throw new Exception("ParserComposer:  cannot compose parser FST until lexica have been installed.")}

    val greek = StringBuilder.newBuilder
    greek.append(header)
    greek.append("#include \"" + projectDir.toString + "/symbols.fst\"\n\n" )

    greek.append("% Dynamically loaded lexica of stems:\n$stems$ = ")

    greek.append(lexiconFiles(lexica).mkString(" |\\\n") + "\n\n")

    greek.append("% Dynamically loaded inflectional rules:\n$ends$ = \"<" + projectDir.toString + "/inflection.a>\"")

    greek.append("\n\n% Morphology data is the crossing of stems and endings:\n$morph$ = $stems$ <div> $ends$\n\n")

    greek.append("% Acceptor (1) filters for content satisfying requirements for\n")
    greek.append("% morphological analysis and  (2) maps from underlying to surface form\n")
    greek.append("% by suppressing analytical symbols, and allowing only surface strings.\n")
    greek.append("$acceptor$ = \"<" + projectDir.toString + "/acceptor.a>\"\n\n" )

    greek.append("% Final transducer:\n")
    greek.append("$morph$ || $acceptor$\n\n")

    val greekFile = projectDir/"greek.fst"
    greekFile.overwrite(greek.toString)
  }


  /**  Compose a single FST string listing all lexicon
  *  files. Note that SFST efficiently uses lexicon files
  * like these directly from uncompiled FST source, so
  * greek.fst can include them without compilation.
  *
  * @param dir Root directory for all lexicon files.
  */
  def lexiconFiles(dir: ScalaFile): Vector[String] = {
    val files = dir.glob("*.fst").toVector
    files.filter(_.nonEmpty).map(f => "\"" + f.toString() + "\"").toVector
  }

}
