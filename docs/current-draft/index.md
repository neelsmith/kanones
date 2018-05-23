---
title: Overview
layout: page
---


Kanónes is a system for building corpus-specific morphological parsers for ancient Greek, represented in a specified orthographic system.  Parsers built with Kanónes take an "analysis by synthesis" approach.  Greek words are stripped of accent, and submitted to a finite state transducer (FST) that collects candidate analyses; an external program then adds the appropriate accent for the suggested form:  if it matches the original, accented word, then the analysis is valid.

(For a fuller description of Kanónes' approach to parsing, see "Morphological Analysis of Historical Languages" in *Bulletin of the Institute for Classical Studies* 59-2, 2016, 89-102.)

Data for a particular parser are read from simple tables in delimited-text format, and a FST is compiled using that dataset.

The data tables are accompanied by an explicit defined of the "alphabet" used to record the data so the same parsing logic can be applied to corpora of Greek texts in the familiar literary Greek orthography, or in the epichoric alphabets of the Classical and Archaic periods.


## Datasets

Data are read from tables of delimited-text data, and automatically converted to statements in the notation of the Stuttgart Finite State Transducer toolkit <http://www.cis.uni-muenchen.de/~schmid/tools/SFST/>.   Parsers are built from two kinds of datasets:

1.  [morphological lexica](Stem-tables) ("stems")
2.  [inflectional rules](Rules-tables)


## The orthographic system

- defining a the [orthographic system](Orthographic-systems) of a corpus

## The parsing logic of the FST

The [logic of the finite state transducer](FST-logic) is independent of the data sets, and written in the Stuttgart Finite State Transducer notation.

## The URN manager

(TBA)

## Debugging utilities

(TBA)
