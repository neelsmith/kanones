package edu.holycross.shot.kanones.builder

import better.files.{File => ScalaFile, _}
import better.files.Dsl._


/** Write the top-level transducer, greek.fst.
*/
object ParserComposer {

  /** Static file header. */
  val header = """
%% morph.fst : a Finite State Transducer for ancient greek morphology.
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

    val morphfst = StringBuilder.newBuilder
    morphfst.append(header)
    morphfst.append("#include \"" + projectDir.toString + "/symbols.fst\"\n\n" )

    morphfst.append("% Dynamically loaded lexica of stems:\n$stems$ = ")

    morphfst.append(lexiconFiles(lexica).mkString(" |\\\n") + "\n\n")

    morphfst.append("% Dynamically loaded inflectional rules:\n$ends$ = \"<" + projectDir.toString + "/inflection.a>\"")

    morphfst.append("\n\n% Morphology data is the crossing of stems and endings:\n$morph$ = $stems$ <div> $ends$\n\n")

    morphfst.append("% Acceptor filters for content satisfying requirements for morphological analysis\n")
    //greek.append("% morphological analysis and  (2) maps from underlying to surface form\n")
    //greek.append("% by suppressing analytical symbols, and allowing only surface strings.\n")
    morphfst.append("$acceptor$ = \"<" + projectDir.toString + "/acceptor.a>\"\n\n" )

    morphfst.append("% Final transducer:\n")
    morphfst.append("$morph$ || $acceptor$\n\n")

    val morphFile = projectDir/"morph.fst"
    morphFile.overwrite(morphfst.toString)

    val greekFile = projectDir/"greek.fst"
    val finalParser = StringBuilder.newBuilder
    finalParser.append("#include \"" + projectDir.toString + "/symbols.fst\"\n\n" )

    finalParser.append("$morph$ = \"<" + projectDir.toString + "/morph.a>\"\n\n")

    finalParser.append("%% Put all symbols in 2 categories:  pass\n%% surface symbols through, suppress analytical symbols.\n#analysissymbol# = #editorial# #urntag# #indecl# #pos# #morphtag# #stemtype#  #separator#\n#surfacesymbol# = #letter# #diacritic#\nALPHABET = [#surfacesymbol#] [#analysissymbol#]:<>\n$stripsym$ = .+\n\n")

    finalParser.append("% Final transducer:\n$morph$ || $stripsym$\n")

    greekFile.overwrite(finalParser.toString)
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
