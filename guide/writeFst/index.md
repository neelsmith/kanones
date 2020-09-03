---
title: "Compiling parsers from tabular data"
layout: page
---

# Compiling parsers from tabular data
A view from the bottom up.


## Stem tables

Stem tables are translated in to FST by the following classes:


- `kanones.builder.*DataInstaller` classes
    - IndeclDataInstaller
    - NounDataInstaller
    - IrregNounDataInstaller
    - IrregPronounDataInstaller
    - AdjectiveDataInstaller
    - IrregAdjectiveDataInstaller
    - VerbDataInstaller
    - IrregVerbDataInstaller
    - IrregInfinitiveDataIntsaller
    - CompoundVerbDataInstaller

## Rules tables

Stem tables are translated in to FST by the following classes:

- `kanones.builder.*RulesInstaller` classes:
    - VerbRulesInstaller (finite verbs)
    - InfinitiveRulesInstaller
    - ParticipleRulesInstaller
    - NounRulesInstaller
    - AdjectiveRulesInstaller



## Orthography and external FST

Outside the stem and rules tables, we have the FST source defining orthography.

- `SymbolsComposer` composes all files in `symbols` directory, maybe these:
    - alphabet.fst     
    - morphsymbols.fst
    - stemtypes.fst
    - markup.fst       
    - phonology.fst

## Higher-order FST and makefiles


The following classes compose higher-order FST files

- `InflectionComposer` composes the top-level `inflections.fst` based on all FST sources composed from rules tables
- `AcceptorComposer` composes top-level  `acceptor.fst`
- `ParserComposer` writes the top-level transducer, `greek.fst`
- `MakefileComposer` writes all makefiles needed to compile FST source


These are all assembled by `BuildComposer`.

Then, parsers can be compiled with `FstCompiler`.
