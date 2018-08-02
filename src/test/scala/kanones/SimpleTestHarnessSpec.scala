
package edu.holycross.shot.kanones

import org.scalatest.FlatSpec
import java.io.File

class SimpleTestHarnessSpec extends FlatSpec {


  val conf = TestConfig("/bin/echo", "/usr/local/bin/fst-infl", "parsers/dev/greek.a")
  val testHarness = SimpleTestHarness(conf)

  "A SimpleTestHarness" should "collect FST reply for a single line of delimited text" in pending
  /*{
    val testLine = "χώρα#noun#feminine#nominative#singular#stempenacc#"
    val fstLines = testHarness.fstAnalyses(testLine)
    val expectedAnalyses = 5
    assert(fstLines.size == expectedAnalyses)
  }

  it should "create a Form object for the expected form" in {
    val testLine = "χώρα#noun#feminine#nominative#singular#stempenacc#"
    val form = testHarness.expectedForm(testLine)

    val expectedForm = NounForm (Feminine, Nominative, Singular)
    form match {
      case actualForm: NounForm => assert(actualForm == expectedForm)
      case _ => fail("Should have creatd a NounForm")
    }
  }

  it should "report if the parser produces the expected reply" in {
    val testLine = "νίκη#noun#feminine#nominative#singular#stempenacc#"
    assert( testHarness.passes(testLine))
  }

  it should "read test specs from a file" in {
    val testFile = new File("src/test/resources/unit_tests_data/smyth/smyth216/smyth216nike.cex")
    val specs = testHarness.testSpecs(testFile)
    val expectedSpecs = 15
    assert(specs.size == expectedSpecs)
  }

  it should "score a set of spcs from a file" in {
    val testFile = new File("src/test/resources/unit_tests_data/smyth/smyth216/smyth216nike.cex")

    val expectedSpecs = 15
    val specs = testHarness.testSpecs(testFile)
    assert(specs.size == expectedSpecs)

    val results = testHarness.score(testFile)
    assert(results.filter(_ == true).size == expectedSpecs)
  }*/
}
