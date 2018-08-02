
package edu.holycross.shot.kanones

import org.scalatest.FlatSpec



import sys.process._
import scala.language.postfixOps
import edu.holycross.shot.greek._
import java.io.PrintWriter
import java.io.File
import scala.io.Source

class ViennaPosSpec extends FlatSpec {

  val litConf = TestConfig("/bin/echo", "/usr/local/bin/fst-infl", "parsers/vienna_lit/greek.a")
  val litTestHarness = SimpleTestHarness(litConf)

  val atticConf = TestConfig("/bin/echo", "/usr/local/bin/fst-infl", "parsers/vienna_attic/greek.a")
  val atticTestHarness = SimpleTestHarness(atticConf)


  "A TestConfig" should "parse a file of forms" in {
    val token = asciiToFst(LiteraryGreekString("καί").stripAccent.ascii)
    val tmp = new File("kanonesTestHarness.txt")
    new PrintWriter(tmp) { write(token); close }
    val reply =  Process(litConf.parseAction + " " + tmp.toString ).!!
    tmp.delete()
    println("reply: " + reply)
  }

  "A TestHarness" should "read specs from a file" in pending /* {
      val testFile = new File("src/test/resources/unit_tests_data/vienna_lit/literary/literaryGreek.cex")
      val testHarness = SimpleTestHarness(litConf)

      val specs = testHarness.testSpecs(testFile)
      println("specs: " + specs.size)

      val results = testHarness.score(testFile)
      println(results)
      //assert(results.filter(_ == true).size == expectedSpecs)
  }
*/

}
