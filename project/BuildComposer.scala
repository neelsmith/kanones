import sbt._

object BuildComposer {

  def apply(srcDir: File) : Unit = {
    composeInflectionMake(srcDir)
  }

  def composeInflectionMake(srcDir: File): Unit = {
    println(s"\nWrite makefile for inflection rules in project ${srcDir}")

  }
}
