package edu.holycross.shot.kanones.builder

import org.scalatest.FlatSpec

import better.files._
import better.files.Dsl._
import java.io.{File => JFile}

class InflectionComposerSpec extends FlatSpec {

  "The InflectionComposer object" should "install files" in  {
    val repo = File(".")
    val datasets = File("src/test/resources")
    val corpus = "minimal"
    RulesInstaller(datasets, repo, corpus)


    val installDir = repo/"parsers"/corpus
    InflectionComposer(installDir)

    val inflfst = installDir/"inflection.fst"
    val fst = inflfst.lines.toVector.filter(_.nonEmpty)

    //tidy up
    (repo/"parsers"/corpus).delete()
    val beginning = "$ending$ ="
    assert(fst(3).startsWith(beginning))
  }
}
