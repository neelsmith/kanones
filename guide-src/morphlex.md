---
title: Morphological datasets
layout: page
---

Your principal task in building a parser with kanónes is to compile the data needed to parse a given corpus.  These datasets are organized as simple tables in delimited-text forma, and are translated into statements in SFST notation in the compilation process.

The string values for stems and endings in these tables must use the alphabet defined for this [orthographic system](Orthographic-systems).

The values for grammatical categories (analytical values, such as gender, case and number for nouns, or classificaitons such as what declension pattern a noun belongs to) must be drawn from the set of symbols defined in the core parser.  They are [sumarrized here](morph-symbols).

The layout of the tables described below is simple.  Parsers are built from two kinds of datasets:

1.  [morphological lexica](Stem-tables) ("stems")
2.  [inflectional rules](Rules-tables)
