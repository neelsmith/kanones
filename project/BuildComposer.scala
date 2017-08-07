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

  def apply(projectDir: File, fstcompiler: String) : Unit = {
    MakefileComposer(projectDir, fstcompiler)
    InflectionComposer(projectDir)
    AcceptorComposer(projectDir)
    ParserComposer(projectDir)
  }

}
