---
title: "Deep notes"
layout: page
---

# Deep notes

Translating FST from tabular data sources:


- `kanones.builder.*DataInstaller` classes
- `kanones.builder.*RulesInstaller` classes

Then composing higher-order FST:


- `ParserComposer` writes the top-level transducer, `greek.fst`
- `InflectionComposer` composes top-level `inflections.fst`
- `AcceptorComposer` composes top-level  `acceptor.fst`
- `SymbolsComposer` composes all files in `symbols` directory, maybe these:
    - alphabet.fst     
    - morphsymbols.fst
    - stemtypes.fst
    - markup.fst       
    - phonology.fst
- `MakefileComposer` writes all makefiles needt to compile FST source
