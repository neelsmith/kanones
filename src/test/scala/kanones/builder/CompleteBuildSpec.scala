
package edu.holycross.shot.kanones.builder

import org.scalatest.FlatSpec
import better.files._
import java.io.{File => JFile}

class CompleteBuildSpec extends FlatSpec {


  "The FstCompiler object" should "compile a binary FST parser" in {
    val repo = File(".")
    val datasource = repo/"datasets"
    val c = "analytical-types"

    val conf = Configuration("/usr/local/bin/fst-compiler-utf8", "/usr/local/bin/fst-infl",  "/usr/bin/make", "datasets")


    FstCompiler.compile(datasource, repo, c, conf, true)
    val parser = repo/"parsers/analytical-types/greek.a"
    assert(parser.exists())
  }

  it should "run the parser and capture output" in pending
}
