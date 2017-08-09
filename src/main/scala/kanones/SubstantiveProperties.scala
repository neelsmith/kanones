package edu.holycross.shot.kanones


sealed trait GrammaticalCase

case object Nominative extends GrammaticalCase
case object Genitive extends GrammaticalCase
case object Dative extends GrammaticalCase
case object Accusative extends GrammaticalCase
case object Vocative extends GrammaticalCase


sealed trait Gender

case object Masculine extends Gender
case object Feminine extends Gender
case object Neuter extends Gender



sealed trait GrammaticalNumber

case object Singular extends GrammaticalNumber
case object Dual extends GrammaticalNumber
case object Plural extends GrammaticalNumber
