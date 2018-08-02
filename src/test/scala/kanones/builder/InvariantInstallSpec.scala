package edu.holycross.shot.kanones.builder

import org.scalatest.FlatSpec
import better.files._
import better.files.File._
import better.files.Dsl._

class InvariantInstallSpec extends FlatSpec {


  "The  RulesInstall object" should "install rules for invariants" in {
    val corpusName = "testCorpus"
    val repo = File(".")
    val parser = repo/"parsers"/corpusName
    val inflTarget = parser/"inflection"
    val inflSrc = repo/"fst/inflection"
    RulesInstaller.installInvariants(inflSrc,inflTarget )
    val expectedFiles = Set (
      inflTarget/"indeclinfl.fst",
      inflTarget/"irreginfl.fst"
    )
    val actualFiles = inflTarget.glob("*.fst").toSet
    assert(actualFiles == expectedFiles)

  }
}
