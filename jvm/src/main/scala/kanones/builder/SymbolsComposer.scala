package edu.holycross.shot.kanones.builder

import better.files.{File => ScalaFile, _}
import better.files.Dsl._


import wvlet.log._
import wvlet.log.LogFormatter.SourceCodeLogFormatter


/** Object composing all files in kanones symbols directory, namely,
* alphabet.fst, morphsymbols.fst, stemtypes.fst, markup.fst and phonology.fst.
*/
object SymbolsComposer extends LogSupport {


  /** Create all FST files defining symbols of a
  * parser's FST alphabet.
  *
  * @param corpusDir Project directory where parser is compiled.
  * @param fstSource
  *
  */
  def apply(corpusDir: ScalaFile, fstSource:  ScalaFile) : Unit = {
    if (! corpusDir.exists) { mkdirs(corpusDir)}
    assert(corpusDir.exists,"SymbolsComposer: failed to make directory " + corpusDir)

    val symbolDir = corpusDir / "symbols"
    if (! symbolDir.exists) {mkdirs(symbolDir)}
    assert(symbolDir.exists,"SymbolsComposer: failed to make directory " + symbolDir)

    composeMainFile(corpusDir)
    copyFst(fstSource / "symbols", symbolDir )

    rewritePhonologyFile(symbolDir / "phonology.fst", corpusDir)
  }

  /** Rewrite phonology.fst to replace references to placeholder string with
  * explicit full path to this installation.  This only works if you've already
  * installed the fst file to rewrite (duh), hence invoked in apply
  * function *after* copyFst.
  *
  * @param f Location of phonology.fst file to rewrite.
  * @param workDir File object for working directory.  This gives
  * us the full path we need to use in rewriting phonology.fst.
  */
  def rewritePhonologyFile(f: ScalaFile, workDir: ScalaFile): Unit = {
    val lines = f.lines.toVector
    val rewritten = lines.map(_.replaceAll("@workdir@", workDir.toString + "/")).mkString("\n")
    f.overwrite(rewritten)
  }


  /** Install symbols from src into project in dest.
  *
  * @param src Directory with fst files defining symbols.
  * @param dest Directory where files should be written. This
  * will be parsers/CORPUS/symbols.
  */
  def copyFst(src: ScalaFile, dest: ScalaFile) : Unit = {
    if (! dest.exists()) {mkdirs(dest)}
    assert(dest.exists, "SymbolsComposer: failed to make directory " + dest)

     val fstFiles = src.glob("*.fst").toVector
     for (f <- fstFiles) {
      val targetFile = dest / f.name
      targetFile.overwrite(f.lines.mkString("\n"))
     }
  }


  /** Write file symbols.fst in project directory.
  *
  * @param projectDir Directory for parser.
  */
  def composeMainFile(projectDir: ScalaFile): Unit = {
    if (! projectDir.exists()) { mkdirs(projectDir)}
    val fst = StringBuilder.newBuilder
    fst.append("% symbols.fst\n% A single include file for all symbols used in this FST.\n\n")

    fst.append("% 1. morphological tags\n")
    fst.append("#include \"" + projectDir.toString + "/symbols/morphsymbols.fst\"\n")
    fst.append("#include \"" + projectDir.toString + "/symbols/stemtypes.fst\"\n\n")

    fst.append("% 2. ASCII representation of polytonic Greek\n")
    fst.append("#include \"" + projectDir.toString + "/symbols/phonology.fst\"\n\n")

    fst.append("% 3. Editorial symbols\n")
    fst.append("#include \"" + projectDir.toString + "/symbols/markup.fst\"\n\n")

    val symbolsFile = (projectDir / "symbols.fst").createIfNotExists()
    assert(symbolsFile.exists,"SymbolsComposer: unable to create " + symbolsFile)
    symbolsFile.overwrite(fst.toString)
  }

}
