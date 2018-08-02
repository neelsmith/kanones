package edu.holycross.shot.kanones.builder

import org.scalatest.FlatSpec
import better.files._
import java.io.{File => JFile}
import better.files.Dsl._

class VerbDataInstallSpec extends FlatSpec {


  "The VerbDataInstaller object" should "throw an exception if given bad data" in {
    try {
      val fst = VerbRulesInstaller.verbRuleToFst("Not a real line")
      fail("Should not have created verb data.")
    } catch {
      case t : Throwable => {
        val beginning = "java.lang.Exception: Wrong number of columns 1."
        assert(t.toString.startsWith(beginning))
      }
    }
  }

  it should "compose FST for a valid CEX string" in {
    val goodLine = "lverbinfl.are_presind1#conj1#o#1st#sg#pres#indic#act"
    val goodFst = VerbRulesInstaller.verbRuleToFst(goodLine)
    val expected = "<conj1><verb>o<1st><sg><pres><indic><act><u>lverbinfl\\.are\\_presind1</u>"
    assert(goodFst.trim ==  expected)
  }

  it should "compose FST from CEX files in a directory" in {
    val repo = File(".")
    val corpusName = "testCorpus"
    val verbDataDir = mkdirs(repo/"datasets"/corpusName/"rules-tables/verbs")
    val goodLine = "lverbinfl.are_presind1#conj1#o#1st#sg#pres#indic#act"
    val cexFile = verbDataDir/"madeuptestdata.cex"
    cexFile.overwrite(goodLine)

    val fstFromDir = VerbRulesInstaller.fstForVerbRules(verbDataDir)

    val lines = fstFromDir.split("\n").toVector
    val expected = "$verbinfl$ =  <conj1><verb>o<1st><sg><pres><indic><act><u>lverbinfl\\.are\\_presind1</u>"
    // tidy up
    (repo/"datasets"/corpusName).delete()

    lines(0) == expected
  }
}
