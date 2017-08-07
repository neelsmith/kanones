import sbt._
import java.io.PrintWriter


object BuildComposer {

  def apply(projectDir: File, corpus: String, fstcompiler: String) : Unit = {
    val srcDir = projectDir / s"parsers/${corpus}"
    InflectionComposer(srcDir, fstcompiler)
    MakefileComposer(srcDir, fstcompiler)
    AcceptorComposer(projectDir, corpus)
  }

}
