import sbt._
import java.io.PrintWriter


object InflectionComposer {

  def apply(projectDir: File, fstcompiler: String) : Unit = {
      println(s"\nWrite makefile for inflection rules in project ${projectDir}")
      val makeFileText = StringBuilder.newBuilder
      makeFileText.append(s"${projectDir.toString}/inflection.a: ")

      val inflDir = projectDir / "inflection"
      val inflFst = (inflDir) ** "*fst"
      val inflFstFiles = inflFst.get
      val dotAs = inflFstFiles.map(_.toString().replaceFirst(".fst$", ".a"))

      makeFileText.append(dotAs.mkString(" ") + "\n")
      makeFileText.append("%.a: %.fst\n\t" + fstcompiler + " $< $@\n")

      val makefile = inflDir / "makefile"
      new PrintWriter(makefile) { write(makeFileText.toString); close }
  }

}
