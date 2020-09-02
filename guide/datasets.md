---
title: Organization of data sets
layout: page
---

Corpus-specific datasets are organized in subdirectories of a root directory specified by the `datasets` setting in your configuration file.  The kanónes build system includes a task `corpus` with the syntax:

    sbt fst CORPUSNAME

Within the `datasets` directory, this creates a subdirectory named `CORPUSNAME` with the full directory layout that kanónes expects when building a parser from your data.

Tabular files defining *inflectional rules* are in subdirectories of of the `rules-tables` directory.  Tabular files defining *lexical items* ("stems") are in subdirectories of the `stems-tables` directory.  In addition to these tables (which may be unique for each corpus to analyze), the `orthography` directory *must* include a file named `alphabet.fst`.  (Typically, many corpora might use an identical alphabet.)  This is a very simple file in the syntax of the Stuttgart Finite State Tooklkit (SFST).


## Subdirectory layout

TBA
