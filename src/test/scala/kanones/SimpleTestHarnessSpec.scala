
package edu.holycross.shot.kanones

import org.scalatest.FlatSpec

class SimpleTestHarnessSpec extends FlatSpec {


  val conf = TestConfig("/bin/echo", "/usr/local/bin/fst-infl", "parsers/dev/greek.a")

  "A SimpleTestHarness" should "collect FST reply for a single line of delimited text" in {

    val testLine = "χώρα#noun#feminine#nominative#singular#stempenacc#"
    val testHarness = SimpleTestHarness(conf)
    val fst = testHarness.fstAnalyses(testLine)

  }
}
