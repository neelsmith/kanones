import sbt._
 import Path.rebase
import scala.io.Source
import java.io.PrintWriter

object RulesInstaller {

  /** Copy data for specified corpus to build area.
  *
  * @param corpus Name of corpus to install.
  */
  def apply(repo: File, corpus: String): Unit = {
    import Path.rebase
    val fstDir = repo / "fst"
    val fstFileOpts = (fstDir) ** "*.fst"
    val fstFiles = fstFileOpts.get

    val buildDir = repo / s"parsers/${corpus}"
    val mappings: Seq[(File,File)] = fstFiles pair rebase(fstDir, buildDir)

    println("\ncopying rules files...")
    for (m <- mappings) {
      IO.copyFile(m._1, m._2)
    }
  }
}
