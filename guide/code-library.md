---
title: The kanónes code library
layout: page
---

Alongside its build system for compiling parsers, kanónes includes a code library for the JVM written in Scala for working with the output of a FST parser built by kanónes.


-   package: `edu.holycross.shot.kanones`
-   [API docs](../api/edu/holycross/shot/kanones/index.html) for version 0.3.0.
-   examples (TBA):
    -   read parser output from a file and construct parse objects from it
    -   find unparseable tokens
    -   find parseable but morphologically ambiguous tokens
    -   find token that parse correctly except for accent
    -   find parse(s) for a specific token
    -   find tokens for a specific parse
    -   find tokens for a part of a parse (e.g., all nouns in nominative case, or all first person verbs)
    -   compile a descriptive inventory for a corpus listing:
        -   all lexical items in the corpus
        -   all inflectional rules used in analying the corpus
        -   all morphological forms appear in the corpus
