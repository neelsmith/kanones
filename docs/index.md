---
title: Overview
layout: page
---

*Kanónes* is a library for building corpus-specific morphological parsers for ancient Greek represented in a specified orthographic system.  Parsers built with kanónes take an "analysis by synthesis" approach.  Greek words are stripped of accent, and submitted to a finite state transducer (*FST*) that collects candidate analyses; an external program then adds the appropriate accent for the suggested form:  if it matches the original, accented word, then the analysis is valid.

(For a fuller description of Kanónes' approach to parsing, see "Morphological Analysis of Historical Languages" in *Bulletin of the Institute for Classical Studies* 59-2, 2016, 89-102.)

Data for a particular parser are read from simple tables in delimited-text format, and a FST is compiled using that dataset.

The data tables are accompanied by an explicit specification of the "alphabet" used to record the data.  This makes it possible to apply the same parsing logic to corpora of Greek texts in the orthography familiar from printed editions, orthographies seen in medieval manuscripts, or even in the epichoric alphabets of the Classical and Archaic periods.

## Current version: **0.3.0**

See [release notes](releases)

[API docs for version 0.3.0](api/edu/holycross/shot/kanones/index.html)

## Building blocks

The kanónes github repository at <https://github.com/neelsmith/kanones> includes the basic logic for parsing Greek morphology, written in the language of the [Stuttgart Finite State Transducer toolbox](http://www.cis.uni-muenchen.de/~schmid/tools/SFST/) (*SFST*).  You supply data for a specific corpus in simple tables (as described below).  The Kanónes library can read your data set, rewrite it in the SFST notation, and combine this with the basic parsing logic to compile a parser.


## Detailed documentation

### Technical prerequisites

-   A POSIX-like environment with `sh`, `echo` and `make`
-   The simple build tool, [sbt](https://github.com/sbt/sbt)
-   The [Stuttgart FST toolbox](http://www.cis.uni-muenchen.de/~schmid/tools/SFST/)


### Installing and using `kanónes`

-   [Installation and configuration](configuration)
-   Managing [your data sets](datasets)
-   [Building and applying a FST parser](parsing)

### The orthographic system

Before you build the morphological datasets for a corpus, you need to specify the orthographic system you will use.

-   defining the [orthographic system](Orthographic-systems) of a corpus



### Morphological datasets

Your principal task in building a parser with kanónes is to compile the data needed to parse a given corpus.  These datasets are organized as simple tables in delimited-text forma, and are translated into statements in SFST notation in the compilation process.

The string values for stems and endings in these tables must use the alphabet defined for this [orthographic system](Orthographic-systems).

The values for grammatical categories (analytical values, such as gender, case and number for nouns, or classificaitons such as what declension pattern a noun belongs to) must be drawn from the set of symbols defined in the core parser.  They are [sumarrized here](morph-symbols).

The layout of the tables described below is simple.  Parsers are built from two kinds of datasets:

1.  [morphological lexica](Stem-tables) ("stems")
2.  [inflectional rules](Rules-tables)

### `sbt` tasks

-   [summary of sbt tasks](sbt-tasks)

### Analyzing output of the parser

Alongside its build system for compiling parsers, kanónes includes a code library for the JVM written in Scala for working with the output of a FST parser built by kanónes.

-   [Using code libraries to work with parsed output](code-library)



### The parsing logic of the FST

In order to use kanónes, it is not necessary to understand the internal logic that the FST follows in parsing morphlogy.  For those who are curious about how morphological can be  expressed independently of any specific data sets, it is briefly [described here](FST-logic).

### The URN manager

(TBA)

### Debugging utilities

(TBA)
