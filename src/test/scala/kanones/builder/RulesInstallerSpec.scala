package edu.holycross.shot.kanones.builder

import org.scalatest.FlatSpec

import better.files._
import better.files.Dsl._
import java.io.{File => JFile}

class RulesInstallerSpec extends FlatSpec {

  "The RulesInstaller object" should "install files" in  {
    val repo = File(".")
    val datasets = File("src/test/resources")
    val corpus = "minimal"

    RulesInstaller(datasets, repo, corpus)

    val inflDir = repo/"parsers"/corpus/"inflection"
    val fstFiles = inflDir.glob("*.fst").toSet
    val expectedFiles = Set(
      inflDir/"indeclinfl.fst",
      inflDir/"irreginfl.fst"
    )
    //tidy up
    (repo/"parsers"/corpus).delete()
    assert(fstFiles == expectedFiles)
  }



   /*

   sourceDir: File, repo: File, corpus: String
   {


      val installDir = repo/"parsers"/corpus



      InflectionComposer(installDir)







  }*/
}
