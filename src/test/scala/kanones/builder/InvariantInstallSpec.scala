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

    //tidy up:
    parser.delete()
    assert(actualFiles == expectedFiles)

  }

  it should "reject bad data" in{
    try {
      val fst = IndeclDataInstaller.indeclLineToFst("Not a real line")
      fail("Should not have installed indeclinable data")
    } catch {
      case t : Throwable => {
        val beginning = "java.lang.Exception: Wrong number of columns 1."
        assert(t.toString.startsWith(beginning))
      }
    }
  }

  it should "convert good data to FST" in {
    val goodLine = "demo.indecl2#lexent.n51951#kai/#indeclconj"
    val goodFst = IndeclDataInstaller.indeclLineToFst(goodLine)

    val expected = "<u>demo\\.indecl2</u><u>lexent\\.n51951</u>kai/<indeclconj>"
    assert(goodFst.trim ==  expected)
  }

  
}
