package edu.holycross.shot.kanones



sealed trait Form

object Form {
  def apply(s: String): Form = {
    val halves = s.split("::")
    require(halves.size == 2, "Could not find :: delimited parts of FST string " + s)
    //val stem = FstStem(halves(0))
    val inflection = FstRule(halves(1))
    //println("STEM: " + stem)
    println("INFL: " + inflection)

    NounForm(Masculine, Nominative, Singular)
  }
}

case class NounForm(gender: Gender, grammaticalCase: GrammaticalCase, grammaticalNumber: GrammaticalNumber) extends Form {}

object NounForm {
  def apply(s: String): NounForm = {
    println("Analyze as a noun: " + s)
    val parts = s.split("<noun>")
    
    throw new Exception("Not yet implemented")
  }
}


case class AdjectiveForm(gender: Gender, grammaticalCase: GrammaticalCase, grammaticalNumber: GrammaticalNumber, degree: Degree) extends Form {}
