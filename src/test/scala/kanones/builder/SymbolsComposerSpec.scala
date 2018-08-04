package edu.holycross.shot.kanones.builder

import org.scalatest.FlatSpec

import better.files._
import better.files.Dsl._
import java.io.{File => JFile}

class SymbolsComposerSpec extends FlatSpec {

  val repo = File(".")
  val corpus = "minimal"


  "The SymbolsComposer object" should "compose the main symbols.fst file" in {
    SymbolsComposer.composeMainFile(repo/"parsers"/corpus)
    val expectedFile = repo/"parsers"/corpus/"symbols.fst"
    val written  = expectedFile.exists()
    // tidy up
    (repo/"parsers"/corpus).delete()
    assert(written)
  }

  it should "copy files from source to build area" in pending

  it should "rewrite phonology files" in pending

  it should "install correct set of files from source" in pending /*{
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
  }*/
}
