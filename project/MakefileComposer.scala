import sbt._
import java.io.PrintWriter


object MakefileComposer {

  def apply(projectDir: File, fstcompiler: String) : Unit = {
    composeMainMake(projectDir, fstcompiler)
  }



  def composeMainMake(projectDir: File, fstcompiler: String): Unit = {
    val makeFileText = StringBuilder.newBuilder
    makeFileText.append(s"${projectDir.toString}/greek.a: ${projectDir.toString}/symbols.fst ${projectDir.toString}/symbols/phonology.fst ${projectDir.toString}/inflection.a ${projectDir.toString}/acceptor.a ${projectDir.toString}/generator.a ")
  }


}
