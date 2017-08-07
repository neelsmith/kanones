import sbt._
import java.io.PrintWriter


/** Object for composing all files that are generated from source,
* rather than build from templates or data sets.  These include
* the necessary makefiles, created by [[MakefileComposer]], and
* the top-level FST files acceptor.fst (created by [[AcceptorComposer]]),
* inflection.fst (created by [[InflectionComposer]]), and greek.fst
* created by [[ParserComposer]].
*/
object BuildComposer {

  def apply(repo: File, corpus: String, fstcompiler: String) : Unit = {
     val corpusDir = "parsers/" + corpus
    val projectDir = repo / corpusDir
    MakefileComposer(projectDir, fstcompiler)
    InflectionComposer(projectDir)
    AcceptorComposer(repo, corpus)
    SymbolsComposer(repo, corpus)
    ParserComposer(projectDir)
  }

}
