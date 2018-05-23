---
title: Overview
layout: page
---

*Kanónes* is a system for building corpus-specific morphological parsers for ancient Greek, represented in a specified orthographic system.  Parsers built with Kanónes take an "analysis by synthesis" approach.  Greek words are stripped of accent, and submitted to a finite state transducer (*FST*) that collects candidate analyses; an external program then adds the appropriate accent for the suggested form:  if it matches the original, accented word, then the analysis is valid.

(For a fuller description of Kanónes' approach to parsing, see "Morphological Analysis of Historical Languages" in *Bulletin of the Institute for Classical Studies* 59-2, 2016, 89-102.)

Data for a particular parser are read from simple tables in delimited-text format, and a FST is compiled using that dataset.

The data tables are accompanied by an explicit specification of the "alphabet" used to record the data.   This makes it possible to apply the same parsing logic to corpora of Greek texts in the orthography familiar from printed editions, orthographies seen in medieval manuscripts, or even in the epichoric alphabets of the Classical and Archaic periods.


## Building blocks

The Kanónes github repository at <https://github.com/neelsmith/kanones> includes the basic logic for parsing Greek morphology, written in the language of the [Stuttgart Finite State Transducer toolbox](http://www.cis.uni-muenchen.de/~schmid/tools/SFST/) (*SFST*).  You supply data for a specific corpus in simple tables (as described below).  The Kanónes repository includes a build system that reads your data set, rewrites in the SFST notation, and combines this with the basic parsing logic to compile a parser.

Technical prerequisites to use Kanónes are:


-   A POSIX-like environment with `sh`, `echo` and `make`
-   The simple build tool, [sbt](https://github.com/sbt/sbt)
-   The [Stuttgart FST toolbox](http://www.cis.uni-muenchen.de/~schmid/tools/SFST/)


## Installing and using `kanónes`

-   [Installation and configuration](configuration)
-   Managing [your data sets](datasets)
-   [Building and using a FST parser](parsing)
-   [Using code libraries to work with parsed output](code-library)



## Datasets

Data are read from tables of delimited-text data, and automatically converted to statements in the notation of the Stuttgart Finite State Transducer toolkit <http://www.cis.uni-muenchen.de/~schmid/tools/SFST/>.   Parsers are built from two kinds of datasets:

1.  [morphological lexica](Stem-tables) ("stems")
2.  [inflectional rules](Rules-tables)


## The orthographic system

-   defining the [orthographic system](Orthographic-systems) of a corpus

## The parsing logic of the FST

The [logic of the finite state transducer](FST-logic) is independent of the data sets, and written in the Stuttgart Finite State Transducer notation.

## The URN manager

(TBA)

## Debugging utilities

(TBA)
