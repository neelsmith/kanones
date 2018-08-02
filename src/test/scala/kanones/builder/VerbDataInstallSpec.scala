package edu.holycross.shot.kanones.builder

import org.scalatest.FlatSpec

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
}
