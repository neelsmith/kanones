package edu.holycross.shot.kanones

import org.scalatest.FlatSpec



class ParserBuilderObjectSpec extends FlatSpec {

  val confFile  = "src/test/resources/conf.properties"
  "The ParserBuilder object" should "build a ParserBuilder" in {
    val pb = ParserBuilder(confFile)
    assert(pb.make == "/usr/bin/make")
  }
}
