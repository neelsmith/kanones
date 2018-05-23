
package edu.holycross.shot.kanones

import org.scalatest.FlatSpec
import java.io.File
import scala.io.Source

class ViennaTestSuiteSpec extends FlatSpec {

  val litConf = TestConfig("/bin/echo", "/usr/local/bin/fst-infl", "parsers/vienna_lit/greek.a")
  val litTestHarness = SimpleTestHarness(litConf)

  val atticConf = TestConfig("/bin/echo", "/usr/local/bin/fst-infl", "parsers/vienna_attic/greek.a")
  val atticTestHarness = SimpleTestHarness(atticConf)


  "A harness for literary Attic Greek" should "score files one at a time" in  {
    val testSrc = new File("src/test/resources/unit_tests_data/vienna_lit")
    val subDirs =  testSrc.listFiles().toVector.filter(_.isDirectory())


    val scores = StringBuilder.newBuilder
    for (dir <- subDirs) {
      scores.append("\n" + dir.getName() + "\n")
      val cex = dir.listFiles().toVector.filter(_.getName().contains(".cex"))
      for (testFile <- cex) {
        val score = litTestHarness.score(testFile)
        scores.append("\t" + testFile.getName() + ": " + score.filter(_ == true).size + "/" + score.size + "\n")
        val lines = Source.fromFile(testFile).getLines.toVector.drop(1)
        for (l <- lines) {
          if (litTestHarness.passes(l)) {
            val cols = l.split({"#"})
            val str = cols(0)
            println("√ " + str + " analyzed as " + cols.drop(1).mkString(", "))
          } else {
            println("x " + l)

          }
        }


      }

    }
    println("Final score: " + scores.toString)

  }


  "A harness for archaic and classica Attic Greek" should "score files one at a time" in pending /*{
    val testSrc = new File("src/test/resources/unit_tests_data/vienna_attic")
    val subDirs =  testSrc.listFiles().toVector.filter(_.isDirectory())


    val scores = StringBuilder.newBuilder
    for (dir <- subDirs) {
      scores.append("\n" + dir.getName() + "\n")
      val cex = dir.listFiles().toVector.filter(_.getName().contains(".cex"))
      for (testFile <- cex) {
        val score = litTestHarness.score(testFile)
        scores.append("\t" + testFile.getName() + ": " + score.filter(_ == true).size + "/" + score.size + "\n")
      }

    }
    println(scores.toString)
  }*/


}
