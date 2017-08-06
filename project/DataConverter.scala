import sbt._

object DataConverter {


  def cexToFst(srcDir: File): Unit = {
    println(s"Convert CEX filesi in ${srcDir} to FST")
    buildNounStems(srcDir)
  }

  def buildNounStems(srcDir: File) = {
    println("\tBuilding nouns stems from " + srcDir)
  }

}
