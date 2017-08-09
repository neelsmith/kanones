package edu.holycross.shot.kanones

import sys.process._
import scala.language.postfixOps
import edu.holycross.shot.greek._
import java.io.PrintWriter
import java.io.File

case class TestConfig(echo: String, fstinfl: String, parser: String) {
  def parseAction: String =  { fstinfl + " " + parser }
}



case class SimpleTestHarness(conf: TestConfig)  {


  /** True if the parser produces an analysis specified by a line
  * of delimited-text data.
  *
  * @param ln  One line of delimited-text data specifying a test.
  */
  def passes(ln: String): Boolean = {
    val expected = expectedForm(ln)
    val formVector = fstAnalyses(ln).map(fst => Form(fst))
    val matchList = formVector.map(_ == expected)
    matchList.contains(true)
  }

  /** Create a [[Form]] object from a line of delimited-text data specifying a test.
  *
  * @param ln  One line of delimited-text data specifying a test.
  */
  def expectedForm(ln: String) : Form = {
    val cols = ln.split("#")

    cols(1) match {
      case "noun" => NounForm(genderForTestLabel(cols(2)), caseForTestLabel(cols(3)), numberForTestLabel(cols(4)))
      case _ => throw new Exception ("Not yet implemented")
    }
  }


  /** Collect FST analyses for a line of delimited-text data specifying a test.
  *
  * @param ln One line of a reply from fst-infl
  */
  def fstAnalyses (ln : String):Vector[String] = {
    val cols = ln.split("#")
    val token = LiteraryGreekString(cols(0)).stripAccent.ascii
    val tmp = new File("kanonesTestHarness.txt")
    new PrintWriter(tmp) { write(token); close }
    val reply =  Process(conf.parseAction + " " + tmp.toString ).!!
    tmp.delete()


    val lines = reply.split("\n").drop(1).toVector
    val tidy = lines.map(_.replaceAll("\\\\:", ":"))
    tidy
  }



}
