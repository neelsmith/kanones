package edu.holycross.shot.kanones



sealed trait MorphologicalProperty

sealed trait GrammaticalCase extends MorphologicalProperty

case object Nominative extends GrammaticalCase
case object Genitive extends GrammaticalCase
case object Dative extends GrammaticalCase
case object Accusative extends GrammaticalCase
case object Vocative extends GrammaticalCase


sealed trait Gender extends MorphologicalProperty

case object Masculine extends Gender
case object Feminine extends Gender
case object Neuter extends Gender



sealed trait GrammaticalNumber extends MorphologicalProperty

case object Singular extends GrammaticalNumber
case object Dual extends GrammaticalNumber
case object Plural extends GrammaticalNumber


sealed trait Degree extends MorphologicalProperty

case object Positive extends Degree
case object Comparative extends Degree
case object Superlative extends Degree


sealed trait Tense extends MorphologicalProperty

case object Present extends Tense
case object Imperfect extends Tense
case object Future extends Tense
case object Aorist extends Tense
case object Perfect extends Tense
case object PluPerfect extends Tense


sealed trait Mood extends MorphologicalProperty
case object Indicative extends Mood
case object Subjunctive extends Mood
case object Optative extends Mood
case object Imperative extends Mood


sealed trait Voice extends MorphologicalProperty
case object Active extends Voice
case object Middle extends Voice
case object Passive extends Voice
