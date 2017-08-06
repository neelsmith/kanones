import sbt._
import java.io.PrintWriter


object BuildComposer {

  def apply(srcDir: File) : Unit = {
    composeInflectionMake(srcDir)
  }


  /** Write makefile for inflection directory.
  *
  * @param srcDir Project directory.
  */
  def composeInflectionMake(srcDir: File): Unit = {
    println(s"\nWrite makefile for inflection rules in project ${srcDir}")
    val makeFileText = StringBuilder.newBuilder
    makeFileText.append(s"${srcDir.toString}/inflection.a: ")

    val inflDir = srcDir / "inflection"
    val inflFst = (inflDir) ** "*fst"
    val inflFstFiles = inflFst.get
    val dotAs = inflFstFiles.map(_.toString().replaceFirst(".fst$", ".a"))

    makeFileText.append(dotAs.mkString(" ") + "\n")
    makeFileText.append("%.a: %.fst\n\tfst-compiler $< $@\n")

    val makefile = inflDir / "makefile"
    new PrintWriter(makefile) { write(makeFileText.toString); close }
  }
}
