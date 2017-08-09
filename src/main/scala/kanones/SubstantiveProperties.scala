package edu.holycross.shot.kanones


sealed trait GrammaticalCase

case object Nominative extends GrammaticalCase
case object Genitive extends GrammaticalCase
case object Dative extends GrammaticalCase
case object Accusative extends GrammaticalCase
case object Vocative extends GrammaticalCase
