---
title: Kanónes
layout: page
---

## Summary

-   [Overview](overview)
-   [API docs for version 1.0.0](api/edu/holycross/shot/kanones/index.html)
-    Current version: **1.0.0**.  See [release notes](releases)



## Technical prerequisites

-   A POSIX-like environment with `sh`, `echo` and `make`
-   The simple build tool, [sbt](https://github.com/sbt/sbt)
-   The [Stuttgart FST tools](http://www.cis.uni-muenchen.de/~schmid/tools/SFST/)

## Installing and using `kanónes`

-   [Installation and configuration](configuration)
-   Managing [your data sets](datasets)
-   [Building and applying a FST parser](parsing)


## Building blocks

The kanónes github repository at <https://github.com/neelsmith/kanones> includes the basic logic for parsing Greek morphology, written in the language of the [Stuttgart Finite State Transducer toolbox](http://www.cis.uni-muenchen.de/~schmid/tools/SFST/) (*SFST*).  You supply data for a specific corpus in simple tables (as described below).  The Kanónes library can read your data set, rewrite it in the SFST notation, and combine this with the basic parsing logic to compile a parser.


-   defining the [orthographic system](Orthographic-systems) of a corpus
-   the URN manager (TBA)
-   assembling [morphological datasets](orthlex)
-   [using code libraries to work with parsed output](code-library)


## The parsing logic of the FST

In order to use kanónes, it is not necessary to understand the internal logic that the FST follows in parsing morphlogy.  For those who are curious about how morphological can be  expressed independently of any specific data sets, it is briefly [described here](FST-logic).
