package edu.holycross.shot.kanones.builder

import org.scalatest.FlatSpec

import better.files._
import better.files.Dsl._
import java.io.{File => JFile}

class AcceptorComposerSpec extends FlatSpec {

  "The AcceptorComposer object" should "compose acceptor files" in {
      // All the prelims before you can comose a parser!
      val repo = File(".")
      val dataSource = File("src/test/resources")
      val corpus = "minimal"
      SymbolsComposer(repo, corpus)
      BuildComposer.installAlphabet(dataSource, repo, corpus)
      DataInstaller(dataSource, repo, corpus)
      RulesInstaller(dataSource, repo, corpus)


      InflectionComposer(repo/"parsers"/corpus)
      AcceptorComposer(repo, corpus)

  }
}
