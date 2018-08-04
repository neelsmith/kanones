package edu.holycross.shot.kanones.builder

import org.scalatest.FlatSpec

import better.files._
import better.files.Dsl._
import java.io.{File => JFile}

class DataInstallerSpec extends FlatSpec {

  "The DataInstaller object" should "install files" in  {
    val repo = File(".")
    val datasets = File("src/test/resources")
    val corpus = "minimal"

    DataInstaller(datasets, repo, corpus)

    val lex = repo/"parsers"/corpus/"lexica/lexicon-indeclinables.fst"

    assert(lex.exists())
    val contents = lex.lines.toVector
      //tidy up
      (repo/"parsers"/corpus).delete()

    assert(contents(0) == "<u>lex\\.indecl2</u><u>lexent\\.n51951</u>kai/<indeclconj>")
  }
}
