---
title: Building and using a parser
layout: page
---


You can build a parser with the sbt task `fst`:


    sbt fst CORPUS [CONFIG_FILE]

This reads either the parser configuration in `CONFIG_FILE` or if not specified from the default file`configuration.properties`, and compiles a SFST parser in `parsers/CORPUS/greek.a`.

You can use regular SFST tools such as `fst-mor` or `fst-infl` with this file.  E.g., to Greek words you supply interactively, run

    fst-mor parsers/CORPUS/greek.a

There is also an `sbt` task to apply your parser to a list of words read from a file with one word per line.  The syntax is:

    sbt parse CORPUS [WORDLIST]

If WORDLIST is not given, the task looks for a file named `wordlist.txt`.
