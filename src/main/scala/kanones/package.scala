
package edu.holycross.shot

package object kanones {

  /** Convert ASCII string to FST symbols.
  *
  * @param ascii Greek string in ASCII representation.
  */
  def asciiToFst(ascii: String): String = {
    ascii.replaceAll("\\(", "<ro>").
          replaceAll( "\\)", "<sm>").
          replaceAll( "\\|", "<isub>").
          replaceAll( "_","<lo>").
          replaceAll( "\\^", "<sh>").
          replaceAll( "-", "<#>")
  }

  /** Convert string in FST symbols to ASCII.
  *
  * @param ascii Greek string in FST symbols.
  */
  def fstToAscii(fst: String): String = {
    fst.replaceAll("<ro>", "(").
      replaceAll("<sm>", ")").
      replaceAll("<isub>", "|").
      replaceAll("<lo>", "_").
      replaceAll("<sh>", "^").
      replaceAll("<#>","-")
  }


  /** Map FST symbol name to [[Gender]].  */
  val genderForFstSymbol: Map[String,Gender] = Map(
    "fem" -> Feminine,
    "masc" -> Masculine,
    "neut" -> Neuter
  )


  /** Map FST symbol name to [[GrammaticalCase]].  */
  val caseForFstSymbol: Map[String, GrammaticalCase] = Map(
    "nom" -> Nominative,
    "gen" -> Genitive,
    "dat" -> Dative,
    "acc" -> Accusative,
    "voc" -> Vocative
  )

  /** Map FST symbol name to [[GrammaticalNumber]].  */
  val numberForFstSymbol: Map[String, GrammaticalNumber] = Map(
    "sg" -> Singular,
    "dual" -> Dual,
    "pl" -> Plural
  )

  /** Map string used in test data tables to [[Gender]].  */
  val genderForTestLabel: Map[String,Gender] = Map(
    "feminine" -> Feminine,
    "masculine" -> Masculine,
    "neuter" -> Neuter
  )

  /** Map string used in test data tables to [[GrammaticalCase]].  */
  val caseForTestLabel: Map[String, GrammaticalCase] = Map(
    "nominative" -> Nominative,
    "genitive" -> Genitive,
    "dative" -> Dative,
    "accusative" -> Accusative,
    "vocative" -> Vocative
  )

  /** Map string used in test data tables to [[GrammaticalNumber]].  */
  val numberForTestLabel: Map[String, GrammaticalNumber] = Map(
    "singular" -> Singular,
    "dual" -> Dual,
    "plural" -> Plural
  )

  /** Map string used in test data tables to [[IndeclinablePoS]].  */
  val indeclinablePoSForTestLabel: Map[String, IndeclinablePoS] = Map(
    "particle" -> Particle,
    "conjunction" -> Conjunction
  )

  val indeclinablePoSForFst: Map[String, IndeclinablePoS] = Map(
    "particle" -> Particle,
    "conjunct" -> Conjunction
  )


  /** Map string used in test data tables to [[Person]].  */
  val personForFstSymbol: Map[String, Person] = Map(
    "1st" -> First,
    "2nd" -> Second,
    "3rd" -> Third
  )

  /** Map string used in test data tables to [[Person]].  */
  val personForTestLabel: Map[String, Person] = Map(
    "first" -> First,
    "second" -> Second,
    "third" -> Third
  )

  /** Map string used in test data tables to [[Tense]].  */
  val tenseForFstSymbol: Map[String, Tense] = Map(
    "pres" -> Imperfect,
    "fut" -> Future,
    "aor" -> Aorist,
    "pft" -> Perfect,
    "plpft" -> Pluperfect
  )

  /** Map string used in test data tables to [[Tense]].  */
  val tenseForTestLabel: Map[String, Tense] = Map(
    "present" -> Imperfect,
    "future" -> Future,
    "aorist" -> Aorist,
    "perfect" -> Perfect,
    "pluperfect" -> Pluperfect
  )

  /** Map string used in test data tables to [[Mood]].  */
  val moodForFstSymbol: Map[String, Mood] = Map(
    "indic" -> Indicative,
    "subj" -> Subjunctive,
    "opt" -> Optative,
    "imptv" -> Imperative
  )

  /** Map string used in test data tables to [[Mood]].  */
  val moodForTestLabel: Map[String, Mood] = Map(
    "indicative" -> Indicative,
    "subjunctive" -> Subjunctive,
    "optative" -> Optative,
    "imperative" -> Imperative
  )

  /** Map string used in test data tables to [[Voice]].  */
  val voiceForFstSymbol: Map[String, Voice] = Map(
    "act" -> Active,
    "mid" -> Middle,
    "pass" -> Passive
  )

  /** Map string used in test data tables to [[Voice]].  */
  val voiceForTestLabel: Map[String, Voice] = Map(
    "active" -> Active,
    "middle" -> Middle,
    "passive" -> Passive
  )
}
