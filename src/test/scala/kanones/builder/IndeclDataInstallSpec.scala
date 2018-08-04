package edu.holycross.shot.kanones.builder

import org.scalatest.FlatSpec
import better.files._
import better.files.File._
import better.files.Dsl._

class IndeclDataInstallSpec extends FlatSpec {


  "The IndeclDataInstaller object" should "throw an exception if given bad data" in{
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

  it should "convert a well-formed CEX String to FST" in {
    val goodLine = "demo.indecl2#lexent.n51951#kai/#indeclconj"
    val goodFst = IndeclDataInstaller.indeclLineToFst(goodLine)

    val expected = "<u>demo\\.indecl2</u><u>lexent\\.n51951</u>kai/<indeclconj>"
    assert(goodFst.trim ==  expected)
  }

  it should "convert data from files in a directory to FST" in {
    val repo = File(".")
    val corpusName = "testCorpus"
    val goodLine = "demo.indecl2#lexent.n51951#kai/#indeclconj"
    val indeclSource = mkdirs(repo/"datasets"/corpusName/"stems-tables/indeclinables")
    val testData = indeclSource/"madeuptestdata.cex"
    val text = s"header line, omitted in parsing\n${goodLine}"
    testData.overwrite(text)
    val fstFromDir = IndeclDataInstaller.fstForIndeclData(indeclSource)
    // Tidy up
    (repo/"datasets"/corpusName).delete()
    val expected = "<u>demo\\.indecl2</u><u>lexent\\.n51951</u>kai/<indeclconj>"
    fstFromDir.trim == expected
  }

  it should "install data from source directory into target director" in {
    val dataSource = "src/test/resources"
    val corpusName = "minimal"
    val lexDir = "parsers"/corpusName/"lexica"
    mkdirs(lexDir)
    val indeclTarget = lexDir/"lexicon-indeclinables.fst"
    IndeclDataInstaller(dataSource/corpusName/"stems-tables/indeclinables",indeclTarget)
    val contents = indeclTarget.lines.toVector
    ("parsers"/corpusName).delete()
    assert(contents.size == 1)
  }
}
