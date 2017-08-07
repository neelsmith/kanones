import sbt._
import java.io.PrintWriter


object BuildComposer {

  def apply(repo: File, corpus: String, fstcompiler: String) : Unit = {
    val projectDir = repo / s"parsers/${corpus}"
    MakefileComposer(projectDir, fstcompiler)
    InflectionComposer(projectDir)
    AcceptorComposer(projectDir)
  }

}
