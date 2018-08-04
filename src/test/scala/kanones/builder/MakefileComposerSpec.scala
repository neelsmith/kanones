package edu.holycross.shot.kanones.builder

import org.scalatest.FlatSpec

import better.files._
import better.files.Dsl._
import java.io.{File => JFile}

class MakefileComposerSpec extends FlatSpec {


  "The MakefileComposer object" should "compose a makefile for the inflection dir" in {
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
    val compiler = "/usr/local/bin/fst-compiler-utf8"


    MakefileComposer.composeInflectionMake(repo/"parsers"/corpus, compiler)
    val inflDir = repo/"parsers"/corpus/"inflection"
    assert(  (inflDir/"makefile").exists() )
  }

  it should "compose the main project makefile" in {
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
    val compiler = "/usr/local/bin/fst-compiler-utf8"


    MakefileComposer.composeMainMake(repo/"parsers"/corpus, compiler)
  }
}
