package edu.holycross.shot.kanones


sealed trait Tense

case object Present extends Tense
case object Imperfect extends Tense
case object Future extends Tense
case object Aorist extends Tense
case object Perfect extends Tense
case object PluPerfect extends Tense


sealed trait Mood
case object Indicative extends Mood
case object Subjunctive extends Mood
case object Optative extends Mood
case object Imperative extends Mood


sealed trait Voice
case object Active extends Voice
case object Middle extends Voice
case object Passive extends Voice
