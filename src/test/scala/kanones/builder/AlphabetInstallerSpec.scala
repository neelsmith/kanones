package edu.holycross.shot.kanones.builder

import org.scalatest.FlatSpec

import better.files._
import better.files.Dsl._
import java.io.{File => JFile}

class AlphabetInstallerSpec extends FlatSpec {

  "The BuildComposer's installAlphabet function" should "correctly copy alphabet.fst" in {

      val repo = File(".")
      val dataSource = File("src/test/resources")
      val corpus = "minimal"
      BuildComposer.installAlphabet(dataSource, repo, corpus)

      val sourceLines = (dataSource/corpus/"orthography/alphabet.fst").lines
      val installedLines = (repo/"parsers"/corpus/"symbols/alphabet.fst").lines
      //tidy up
      (repo/"parsers"/corpus).delete()
      assert(sourceLines == installedLines)

  }
}
