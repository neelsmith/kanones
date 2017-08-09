package edu.holycross.shot.kanones

trait FstRule

/**
*/
case class NounRule(ruleId: String,gender: String, grammaticalCase: String,
grammaticalNumber:String, declClass: String, ending: String ) extends FstRule

object NounRule {

  /** Create full [[NounRule]] object from noun-specific FST.
  *
  */
  def apply(declClass: String, nounData: String): NounRule = {
    val dataRE  = "([^<]+)<([^<]+)><([^<]+)><([^<]+)><u>(.+)<\\/u>".r
    val dataRE(ending, gender, grammCase, grammNumber,ruleId) = nounData
    NounRule(ruleId, gender, grammCase, grammNumber, declClass, ending)
  }

}

object FstRule {

  /** Create an [[FstRule]] object from the FST
  * representation of a rule.
  *
  * @param fst The "rule" half of an FST reply.
  */
  def apply(fst: String): FstRule = {
    println("\nCreate FstRule from " + fst + "\n")
    val idsRE = "<([^<]+)><([^<]+)>(.+)".r
    val idsRE(inflClass, stemType, remainder) = fst

    stemType match {
      case "noun" => NounRule(inflClass,  remainder)
      case s: String => throw new Exception(s"Type ${s} not implemented")
    }
  }

}
