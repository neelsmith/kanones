---
title: Kanónes
layout: page
---

## Overview

-   [Overview](overview)
-   [API docs for version 0.3.0](api/edu/holycross/shot/kanones/index.html)


## Current version: **0.3.0**

See [release notes](releases)


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
