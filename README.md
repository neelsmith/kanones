# Kanónes

Kanónes is a Greek morphological parser.  Its rationale and approach to parsing a historical language is described in "Morphological Analysis of Historical Languages" (*Bulletin of the Institute for Classical Studies* 59-2, 2016, 89-102).


## Prerequisites

- A POSIX-like environment with `sh`, `echo` and `make`
- Scala and sbt
- [Stuttgart FST toolbox](http://www.cis.uni-muenchen.de/~schmid/tools/SFST/).


## `sbt` usage

- create a template data set in `datasets` directory: `sbt corpus CORPUSNAME`
- compile a parser for a corpus:  `sbt fst CORPUSNAME` (where `CORPUSNAME` is a dataset in the `datasets` directory)
- delete all parsers:  `sbt cleanAll`
