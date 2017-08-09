
package edu.holycross.shot.kanones

import org.scalatest.FlatSpec

class FstParsingSpec extends FlatSpec {
  val res1 = "<u>smyth.n47039_0</u><u>lexent.n47039</u>ni<lo>k<noun><fem><h_hs><stemultacc>::<h_hs><noun>h<fem><nom><sg><u>nouninfl.h_hs1</u>"

  val res2 = "<u>smyth.n47039_0</u><u>lexent.n47039</u>ni<lo>k<noun><fem><h_hs><stemultacc>::<h_hs><noun>h<fem><voc><sg><u>nouninfl.h_hs5</u>"

    "The Form object" should "construct Forms from FST input" in {

    val f = Form(res1)
    //println("GOT FORM " + f)
  }

  "The FstStem object" should "parse the stem part of an FST reply into an FstStem object" in {
    val stemFst = "<u>smyth.n47039_0</u><u>lexent.n47039</u>ni<lo>k<noun><fem><h_hs><stemultacc>"

    val stemObj = FstStem(stemFst)
    println("Gof stem object " + stemObj)
  }

}
