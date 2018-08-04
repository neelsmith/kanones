package edu.holycross.shot.kanones.builder

import org.scalatest.FlatSpec

import better.files._
import better.files.Dsl._
import java.io.{File => JFile}

class SymbolsComposerSpec extends FlatSpec {

  "The SymbolsComposer object" should "install files" in {

      val repo = File(".")
      val corpus = "minimal"
      SymbolsComposer(repo, corpus)

      val symbolsDir = repo/"parsers"/corpus/"symbols"
      val fstFiles = symbolsDir.glob("*.fst").toSet
      val expectedFiles = Set(
        symbolsDir/"markup.fst",
        symbolsDir/"morphsymbols.fst",
        symbolsDir/"phonology.fst",
        symbolsDir/"stemtypes.fst"
      )

      //tidy up
      (repo/"parsers"/corpus).delete()
      assert(fstFiles == expectedFiles)

  }
}
