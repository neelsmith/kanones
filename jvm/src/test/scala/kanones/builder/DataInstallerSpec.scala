package edu.holycross.shot.kanones.builder

import org.scalatest.FlatSpec
import better.files._
import java.io.{File => JFile}
import better.files.Dsl._



class DataInstallerSpec extends FlatSpec {

  val r = scala.util.Random

  "The DataInstaller object" should "install data" in {
    val datasets = File("jvm/src/test/resources/datasets")
    val c = Vector("analytical_types")
    val tempParserDir =  File("jvm/src/test/resources/parsers") / s"dummyparser-${r.nextInt(1000)}"


    val  di = DataInstaller(datasets, c, tempParserDir)
  }
}
