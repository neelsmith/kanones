package edu.holycross.shot.kanones.builder

import org.scalatest.FlatSpec
import better.files._
import java.io.{File => JFile}
import better.files.Dsl._



class IndeclDataInstallerSpec extends FlatSpec {

  val r = scala.util.Random

  "The IndeclDataInstaller object" should "install data" in {
    val datasets = File("jvm/src/test/resources/datasets")
    val corpora = Vector("analytical_types")
    val tempParserDir =  File("jvm/src/test/resources/parsers") / s"dummyparser-${r.nextInt(1000)}"
    // Create directory for lexica:
    val targetDir = tempParserDir / "lexica"
    if (targetDir.exists) {
      tempParserDir.delete()
    }
    mkdirs(targetDir)
    assert(targetDir.exists,"IndeclDataInstaller:  could not create " + targetDir)


    val targetFile = targetDir / "lexicon-indeclinables.fst"

    IndeclDataInstaller(datasets, corpora, targetFile)
    assert(targetFile.exists,"IndeclDataInstaller:  did not create destination file " + targetFile)
    /*

    val expectedLines = Vector("<u>proof\\.indecl1</u><u>lexent\\.n11873</u>cum<indecl><indeclconj>")
    assert(targetFile.lines.toVector.filter(_.nonEmpty) == expectedLines)

    //println("WROTE : " + targetFile.lines.toVector.filter(_.nonEmpty) )

    tempParserDir.delete()
    */

  }
}
