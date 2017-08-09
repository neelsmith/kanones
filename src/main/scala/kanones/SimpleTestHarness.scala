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


  /** Collect FST analyses for a line of test data.
  */
  def fstAnalyses (ln : String) = {
    val cols = ln.split("#")
    val token = LiteraryGreekString(cols(0)).stripAccent.ascii
    val tmp = new File("kanonesTestHarness.txt")
    new PrintWriter(tmp) { write(token); close }
    //println("NEED TO EXEC: " + s"echo ${token} #|  ${conf.parseAction}")
    //s"echo ${token}" #|  conf.parseAction !!
    //val reply =  cmd !!
    //println("GOT THIS: "+ repl)
    //val rp1: String = "echo xwra" #|  "/usr/local/bin/fst-infl parsers/dev/greek.a" !!
    //val r = Process(s"echo ${token}") #| Process(conf.parseAction).!!
    //println(rp)
    val r =  Process(conf.parseAction + " " + tmp.toString ).!!
    tmp.delete()
    val lines = r.split("\n").drop(1).toVector
    println("REPLY IS " + lines)
  }



}
