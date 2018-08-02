
package edu.holycross.shot.kanones

import org.scalatest.FlatSpec
import java.io.File

class SmythTestSuiteSpec extends FlatSpec {

  val conf = TestConfig("/bin/echo", "/usr/local/bin/fst-infl", "parsers/dev/greek.a")
  val testHarness = SimpleTestHarness(conf)


  "A SimpleTestHarness" should "score files one at a time" in pending /* {
    val testSrc = new File("src/test/resources/unit_tests_data/smyth")
    val subDirs =  testSrc.listFiles().toVector.filter(_.isDirectory())


    val scores = StringBuilder.newBuilder
    for (dir <- subDirs) {
      scores.append("\n" + dir.getName() + "\n")
      val cex = dir.listFiles().toVector.filter(_.getName().contains(".cex"))
      for (testFile <- cex) {
        val score = testHarness.score(testFile)
        scores.append("\t" + testFile.getName() + ": " + score.filter(_ == true).size + "/" + score.size + "\n")
      }

    }
    println(scores.toString)

  }
*/
}
