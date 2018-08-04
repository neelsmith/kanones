package edu.holycross.shot.kanones.builder

import org.scalatest.FlatSpec

import better.files._
import better.files.Dsl._
import java.io.{File => JFile}

class ParserComposerSpec extends FlatSpec {

  "The ParserComposer object" should "compose parsingfiles" in {
      // All the prelims before you can comose a parser!
      val repo = File(".")
      val dataSource = File("src/test/resources")
      val corpus = "minimal"
      SymbolsComposer(repo, corpus)
      BuildComposer.installAlphabet(dataSource, repo, corpus)
      RulesInstaller(dataSource, repo, corpus)
      DataInstaller(dataSource, repo, corpus)


      InflectionComposer(repo/"parsers"/corpus)
      AcceptorComposer(repo, corpus)
      ParserComposer(repo/"parsers"/corpus)

      val projectDir = "parsers"/corpus
      val newFiles = Vector(
        projectDir/"morph.fst",
        projectDir/"greek.fst"
      )
      for (f <- newFiles) {
        assert(f.exists() && f.lines.toVector.nonEmpty)
      }
  }
}
