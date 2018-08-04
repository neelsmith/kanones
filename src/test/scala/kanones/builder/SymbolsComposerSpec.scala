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

  it should "copy files from source to build area" in {
    val symbolsDir = repo/"parsers"/corpus/"symbols"
    SymbolsComposer.copyFst(repo/"fst/symbols", symbolsDir )
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

  it should "rewrite phonology files" in {
    val symbolsDir = repo/"parsers"/corpus/"symbols"
    SymbolsComposer.copyFst(repo/"fst/symbols", symbolsDir )
    SymbolsComposer.rewritePhonologyFile(repo/"parsers"/corpus/"symbols/phonology.fst", repo/"parsers"/corpus)
    val rewritten = (symbolsDir/"stemtypes.fst").exists()
    //tidy up
    (repo/"parsers"/corpus).delete()
    assert( rewritten )
  }

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
