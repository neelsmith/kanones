# Kanónes

Kanónes is a system for building Greek morphological parsers.

-  see the [in-progress User's Guide](https://neelsmith.github.io/kanones/)
-  read more about its rationale and approach to parsing a historical language in "Morphological Analysis of Historical Languages" (*Bulletin of the Institute for Classical Studies* 59-2, 2016, 89-102).


## Prerequisites

-   A POSIX-like environment with `sh`, `echo` and `make`
- [Scala](https://www.scala-lang.org/) and [sbt](https://github.com/sbt/sbt)
-   [Stuttgart FST toolbox](http://www.cis.uni-muenchen.de/~schmid/tools/SFST/).


## Cheat sheet for `sbt` usage

-   create a template data set in `datasets` directory: `sbt corpus [-r] CORPUSNAME` (where `-r` means "delete and replace" any existing dataset named `CORPUSNAME`)
-   compile a parser for a corpus:  `sbt fst CORPUSNAME` (where `CORPUSNAME` is a dataset in the `datasets` directory)
-   delete all parsers:  `sbt cleanAll`
-   install utilities for debugging the FST built for a particular corpus: `sbt utils CORPUSNAME`
