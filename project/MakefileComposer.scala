import sbt._
import java.io.PrintWriter


/** Write makefiles to compile FST.
*/
object MakefileComposer {

  /** Write makefiles for Kanónes project in a given directory.
  *
  * @param projectDir Directory for corpus-specific parser.
  * @param fstcompiler Full path to FST compiler.
  */
  def apply(projectDir: File, fstcompiler: String) : Unit = {
    val inflDir = projectDir / "inflection"


    composeInflectionMake(projectDir, fstcompiler)
    //composeVerbStemMake(projectDir, fstcompiler)

    composeMainMake(projectDir, fstcompiler)
  }


  /** Collect a list of file names in `.a` for `.fst` files
  * in a given directory.
  *
  * @param dir Directory with .fst files.
  */
  def dotAsForFst(dir: File) : Vector[String] = {
    val fst = (dir) * "*fst"
    val fstFiles = fst.get
    fstFiles.map(_.toString().replaceFirst(".fst$", ".a")).toVector
  }



  /** Collect all subdirectories of a given directory.
  *
  * @param dir Directory to look in for subdirectories.
  */
  def subDirs(dir: File) : Vector[File] = {
    val children = dir.listFiles()
    children.filter(_.isDirectory).toVector
  }

  def composeMainMake(projectDir: File, fstcompiler: String): Unit = {
    val makeFileText = StringBuilder.newBuilder
    makeFileText.append(s"${projectDir.toString}/greek.a: ${projectDir.toString}/symbols.fst ${projectDir.toString}/symbols/phonology.fst ${projectDir.toString}/inflection.a ${projectDir.toString}/acceptor.a \n")

    val dotAs = dotAsForFst(projectDir / "acceptors").mkString(" ")
    makeFileText.append(s"${projectDir.toString}/verb.a: " + dotAs + "\n\n")

    makeFileText.append(s"${projectDir.toString}/acceptor.a: ${projectDir.toString}/verb.a\n\n")
    makeFileText.append(verbStemMake(projectDir, fstcompiler))



/*
    for (d <- subDirs(projectDir / "acceptors")) {
      val subDotAs = dotAsForFst(d)
      makeFileText.append(d.toString() + ".a: " + subDotAs + "\n\n")
    }
*/


    //("DOT AS WERE " + dotAs.mkString("\n"))
    //val acceptorsFst = (projectDir / "acceptors") ** "*fst"
    //val acceptorsFstFiles = acceptorsFst.get
    //val dotAs = acceptorsFst.map(_.toString().replaceFirst(".fst$", ".a"))
//    makeFileText.append(dotAs.mkString(" ") + "\n")



    makeFileText.append("%.a: %.fst\n\t" + fstcompiler + " $< $@\n")
     //later:  ${projectDir.toString}/generator.a ")

    val makeFile = projectDir / "makefile"
    new PrintWriter(makeFile) { write(makeFileText.toString); close }
  }



  /** Compose makefile for inflection subdirectory.
  */
  def verbStemMake(projectDir: File, fstcompiler: String) : String = {
    (s"\nWrite makefile for verb stem trandsducers in project ${projectDir}\n")
    val makeFileText = StringBuilder.newBuilder
    makeFileText.append(s"${projectDir.toString}/acceptors/verbstems.a: ")
    val inflDir = projectDir / "acceptors/verb"
    val inflFst = (inflDir) ** "*fst"
    val inflFstFiles = inflFst.get
    val dotAs = inflFstFiles.map(_.toString().replaceFirst(".fst$", ".a"))

    makeFileText.append(dotAs.mkString(" ") + "\n")
    makeFileText.toString
    //makeFileText.append("%.a: %.fst\n\t" + fstcompiler + " $< $@\n")

  //  val makefile = projectDir / "acceptors/verb/makefile"
  //  new PrintWriter(makefile) { write(makeFileText.toString); close }
  }

  /** Compose makefile for inflection subdirectory.
  */
  def composeInflectionMake(projectDir: File, fstcompiler: String) : Unit = {
      (s"\nWrite makefile for inflection rules in project ${projectDir}\n")
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
