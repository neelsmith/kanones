---
layout: page
title:  SBT tasks
---


Kanónes' build file defines a series of sbt tasks.  You can either run these from a shell (as illustrated below), or you can start an interactive sbt session (with the command `sbt`), and run them by omitting the initial `sbt` from the examples:

-   create a template data set in `datasets` directory: `sbt corpus [-r] CORPUSNAME` (where `-r` means "delete and replace" any existing dataset named `CORPUSNAME`)
-   compile a parser for a corpus:  `sbt fst CORPUSNAME` (where `CORPUSNAME` is a dataset in the `datasets` directory)
-   delete all parsers:  `sbt cleanAll`
-   install utilities for debugging the FST built for a particular corpus: `sbt utils CORPUSNAME`
