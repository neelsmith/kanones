import sbt._
import java.io.PrintWriter


object MakefileComposer {

  def apply(projectDir: File, fstcompiler: String) : Unit = {
    val inflDir = projectDir / "inflection"
    composeInflectionMake(inflDir, fstcompiler)

    composeMainMake(projectDir, fstcompiler)
  }


  def dotAsForFst(dir: File) : Vector[String] = {
    val fst = (dir) * "*fst"
    val fstFiles = fst.get
    fstFiles.map(_.toString().replaceFirst(".fst$", ".a")).toVector
  }

  def subDirs(dir: File) : Vector[File] = {
    val children = dir.listFiles()
    children.filter(_.isDirectory).toVector
  }

  def composeMainMake(projectDir: File, fstcompiler: String): Unit = {
    val makeFileText = StringBuilder.newBuilder
    makeFileText.append(s"${projectDir.toString}/greek.a: ${projectDir.toString}/symbols.fst ${projectDir.toString}/symbols/phonology.fst ${projectDir.toString}/inflection.a ${projectDir.toString}/acceptor.a \n")

    val dotAs = dotAsForFst(projectDir / "acceptors").mkString(" ")
    makeFileText.append(s"${projectDir.toString}/acceptor.a: " + dotAs + "\n\n")


/*
    for (d <- subDirs(projectDir / "acceptors")) {
      val subDotAs = dotAsForFst(d)
      makeFileText.append(d.toString() + ".a: " + subDotAs + "\n\n")
    }
*/


    //println("DOT AS WERE " + dotAs.mkString("\n"))
    //val acceptorsFst = (projectDir / "acceptors") ** "*fst"
    //val acceptorsFstFiles = acceptorsFst.get
    //val dotAs = acceptorsFst.map(_.toString().replaceFirst(".fst$", ".a"))
//    makeFileText.append(dotAs.mkString(" ") + "\n")



    makeFileText.append("%.a: %.fst\n\t" + fstcompiler + " $< $@\n")
     //later:  ${projectDir.toString}/generator.a ")

    val makeFile = projectDir / "makefile"
    new PrintWriter(makeFile) { write(makeFileText.toString); close }
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
