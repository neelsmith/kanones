
package edu.holycross.shot.kanones

import org.scalatest.FlatSpec

class FstRuleParsingSpec extends FlatSpec {

  "The FstRule object" should "parse the stem part of an FST reply into an FstRule object" in {
    val ruleFst = "<h_hs><noun>h<fem><voc><sg><u>nouninfl.h_hs5</u>"

    val rule = FstRule(ruleFst)
    rule match {
      case nr: NounRule => {
        assert(nr.ruleId == "nouninfl.h_hs5")      
      }
    }

//ruleId: String,gender: String, grammaticalCase: String,
//grammaticalNumber:String, declClass: String, ending: String
  }



}
