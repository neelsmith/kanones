package edu.holycross.shot.kanones



sealed trait Form

object Form {

  /** From a raw FST string, identify a morphological form.
  *
  * @param s String value of a single FST analysis.
  */
  def apply(s: String): Form = {
    val halves = s.split("::")
    require(halves.size == 2, "Could not find :: delimited parts of FST string " + s)

    val inflection = FstRule(halves(1))

    inflection match {
      case nr: NounRule => {
        NounForm(nr.gender, nr.grammaticalCase, nr.grammaticalNumber)
      }
      case _ => throw new Exception("Form class not yet implemented.")
    }
  }
}

case class NounForm(gender: Gender, grammaticalCase: GrammaticalCase, grammaticalNumber: GrammaticalNumber) extends Form {}


object NounForm {


  /** Create [[NounForm]] from three FST symbols.
  */
  def apply(g: String, c: String, n: String): NounForm = {
    NounForm(genderForFstSymbol(g), caseForFstSymbol(c), numberForFstSymbol(n))
  }
}


case class AdjectiveForm(gender: Gender, grammaticalCase: GrammaticalCase, grammaticalNumber: GrammaticalNumber, degree: Degree) extends Form {}
