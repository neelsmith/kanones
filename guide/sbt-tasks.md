---
layout: page
title:  SBT tasks
---


Kanónes' build file defines a series of sbt tasks.  You can either run these from a shell (as illustrated below), or you can start an interactive sbt session (with the command `sbt`), and run them by omitting the initial `sbt` from the examples.

Create a template data set in the currently defined datasets directory:

    sbt corpus [-r] CORPUSNAME

where `-r` means "delete and replace" any existing dataset named `CORPUSNAME`.

Compile a parser for a corpus:

    sbt fst CORPUSNAME


where `CORPUSNAME` is a dataset in the `datasets` directory.

Delete all compiled parsers from the `parsers` directory:

    sbt cleanAll

Install utilities for debugging the FST built for a particular corpus:

    sbt utils CORPUSNAME
