---
title: Overview
layout: page
---

*Kanónes* is a library for building corpus-specific morphological parsers for ancient Greek represented in a specified orthographic system.  Parsers built with kanónes take an "analysis by synthesis" approach.  Greek words are stripped of accent, and submitted to a finite state transducer (*FST*) that collects candidate analyses; an external program then adds the appropriate accent for the suggested form:  if it matches the original, accented word, then the analysis is valid.

(For a fuller description of Kanónes' approach to parsing, see "Morphological Analysis of Historical Languages" in *Bulletin of the Institute for Classical Studies* 59-2, 2016, 89-102.)

Data for a particular parser are read from simple tables in delimited-text format, and a FST is compiled using that dataset.

The data tables are accompanied by an explicit specification of the "alphabet" used to record the data.  This makes it possible to apply the same parsing logic to corpora of Greek texts in the orthography familiar from printed editions, orthographies seen in medieval manuscripts, or even in the epichoric alphabets of the Classical and Archaic periods.
