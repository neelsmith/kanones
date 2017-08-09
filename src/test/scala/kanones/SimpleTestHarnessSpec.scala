
package edu.holycross.shot.kanones

import org.scalatest.FlatSpec

class SimpleTestHarnessSpec extends FlatSpec {


  val conf = TestConfig("/bin/echo", "/usr/local/bin/fst-infl", "parsers/dev/greek.a")
  val testHarness = SimpleTestHarness(conf)

  "A SimpleTestHarness" should "collect FST reply for a single line of delimited text" in {

    val testLine = "χώρα#noun#feminine#nominative#singular#stempenacc#"
    val fstLines = testHarness.fstAnalyses(testLine)
    val expectedAnalyses = 5
    assert(fstLines.size == expectedAnalyses)
  }

  it should "create a Form object for the expected form" in {
    val testLine = "χώρα#noun#feminine#nominative#singular#stempenacc#"
    val form = testHarness.requestedForm(testLine)
    val expectedForm = NounForm (Feminine, Nominative, Singular)
    form match {
      case actualForm: NounForm => assert(actualForm == expectedForm)
      case _ => fail("Should have creatd a NounForm")
    }
  }
}
