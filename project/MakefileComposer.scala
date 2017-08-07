import sbt._
import java.io.PrintWriter


object MakefileComposer {

  def apply(projectDir: File, fstcompiler: String) : Unit = {
    val inflDir = projectDir / "inflection"
    composeInflectionMake(inflDir, fstcompiler)

    composeMainMake(projectDir, fstcompiler)
  }



  def composeMainMake(projectDir: File, fstcompiler: String): Unit = {
    val makeFileText = StringBuilder.newBuilder
    makeFileText.append(s"${projectDir.toString}/greek.a: ${projectDir.toString}/symbols.fst ${projectDir.toString}/symbols/phonology.fst ${projectDir.toString}/inflection.a ${projectDir.toString}/acceptor.a ${projectDir.toString}/generator.a ")

    println("Main makefile:\n\n\n" + makeFileText.toString )
  }

  def composeInflectionMake(inflDir: File, fstcompiler: String) : Unit = {
      println(s"\nWrite makefile for inflection rules in project ${inflDir}")
      val makeFileText = StringBuilder.newBuilder
      makeFileText.append(s"${inflDir.toString}/inflection.a: ")

      val inflFst = (inflDir) ** "*fst"
      val inflFstFiles = inflFst.get
      val dotAs = inflFstFiles.map(_.toString().replaceFirst(".fst$", ".a"))

      makeFileText.append(dotAs.mkString(" ") + "\n")
      makeFileText.append("%.a: %.fst\n\t" + fstcompiler + " $< $@\n")

      val makefile = inflDir / "makefile"
      new PrintWriter(makefile) { write(makeFileText.toString); close }
  }


}
