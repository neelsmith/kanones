import sbt._

object VariableManager {


  def expandVariables(srcDir: File): Unit = {
    println(s"expand varaibles in all FST source files and makefiles in ${srcDir}")

  }
}
