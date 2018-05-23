---
title: FST symbols
layout: page
---

A finite state transducer transforms symbols between a generated, or surface, form and an analysis.

The vocabulary of symbols used in Kanónes is defined in the file `symbols.fst` located in the root directory of the build.  This file in turn uses `include` statements to draw together symbol definitions from five files in the adjacent `symbols` directory.  Together, these define the complete set of symbols recognized by the FST, so any transducer has access to the full symbol set by including `symbols.fst`.


## Organization of definitions in the `symbols` directory

Within the `symbols` directory, definitions are organized as follows:

- `alphabet.fst`:  maps categories of letters in a particular Greek writing system (traditional literary Greek, or one of the epichoric alphabets) onto ascii characters, including named symbols for letters that are recognized by some portions of the parsing logic for particular treatment.  This file *must* be included in the project-specific data set, and is automatically added here by the build process.
- `markup.fst`:  special symbols for separating stem from inflectional component in the morphological database, and marking the begnning and ends of URN values in the data.
- `morphsymbols.fst`:  defines symbols for analytical values (such as "gender", "case" and "number" for nouns).
- `phonology.fst`:  defines all legal symbols in the FST other than the morphological categories defined in `stemtypes.fst`
- `stemtypes.fst`: defines symbols in the FST for morphological categories for stems.
