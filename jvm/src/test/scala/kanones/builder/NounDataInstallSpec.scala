package edu.holycross.shot.kanones.builder

import org.scalatest.FlatSpec
import better.files._
import java.io.{File => JFile}
import better.files.Dsl._

import java.util.Calendar

class NounDataInstallSpec extends FlatSpec {

  val r = scala.util.Random
  val millis = Calendar.getInstance().getTimeInMillis()
  r.setSeed(millis)


  "The NounDataInstaller object" should  "install noun data from a single source" in  {
    val datasets = File("jvm/src/test/resources/datasets/")
    val corpora = Vector("analytical_types")
    val tempParserDir = File(s"jvm/src/test/resources/parsers/dummyparser-${r.nextInt(1000)}")
    val targetDir = tempParserDir / "inflection"
    if (targetDir.exists) {
      tempParserDir.delete()
    }
    mkdirs(targetDir)
    assert(targetDir.exists,"NounDataInstaller:  could not create " + targetDir)

    val targetFile = targetDir / "nouninfl.fst"

    NounDataInstaller(datasets, corpora, targetFile)
    assert(targetFile.exists,"NounDataInstaller:  did not create destination file " + targetFile)
    val expected = "<u>lex\\.noun1</u><u>lsj\\.n8909</u>a<sm>nqrwp<noun><masc><os_ou><stempenacc>"
    assert(targetFile.lines.toVector.filter(_.nonEmpty).head == expected)
    tempParserDir.delete()
  }
}
