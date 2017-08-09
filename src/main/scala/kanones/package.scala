
package edu.holycross.shot

package object kanones {




  def fstSymbolsToAscii(fst: String): String = {
    fst.replaceAll("<ro>", "(").
      replaceAll("<sm>", ")").
      replaceAll("<isub>", "|")
    //surfaceFormatted.replaceAll(rightmostUrn,"").replaceAll(semanticTags, "")
    //return surfaceFormatted
  }

  val genderForFstSymbol: Map[String,Gender] = Map(
    "fem" -> Feminine,
    "masc" -> Masculine,
    "neut" -> Neuter
  )

  val caseForFstSymbol: Map[String, GrammaticalCase] = Map(
    "nom" -> Nominative,
    "gen" -> Genitive,
    "dat" -> Dative,
    "acc" -> Accusative,
    "voc" -> Vocative
  )

    val numberForFstSymbol: Map[String, GrammaticalNumber] = Map(
      "sg" -> Singular,
      "dual" -> Dual,
      "pl" -> Plural
    )

}
