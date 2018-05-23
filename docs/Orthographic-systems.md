---
title: Orthographic systems
layout: page
---


For a given morphological data set, the single file `orthography/alphabet.fst` defines the set of characters allowed in the data tables.  This file is written in SFST syntax:  if you want to customize your alphabet, you can refer to this [documentation of SFST](http://www.cis.uni-muenchen.de/~schmid/tools/SFST/data/SFST-Manual.pdf), but most frequently you will simply reuse an existing alphabet definition.


## Understanding the default specification

A default alphabet specification lists a set of characters for consonants and vowels, following a long-standing convention used by classicists to represent the Greek alphabet in ASCII characters.  (It is reproduced at the bottom of this page.) Several characters are individually defined in order to account for phonological changes resulting from combining stems and endings.

In this convention, the iota subscript is normally written with the pipe character `|`, but this complicates the pattern matching using regular expressions that the FST relies on.  Instead, we define iota subscript not with a single ASCII character, but with the string `<isub>`.  Strings contained by angle brackets define a single symbol in the alhabet of the FST.  The sequence eta + iota subscript could therefore be written in this alphabet as `h<isub>`.

Indpendently of individual alphabets, kanónes defines four such symbols:

-   smooth breathing: `<sm>`
-   rough breathing: `<ro>`
-   macron (long mark): `<lo>`
-   breve (short mark): `<sh>`

In constructing your data tables, you must use these FST symbols instead of individual characters.  The symbols must *follow* the vowel they are normally written above in print editions.  Alpha + smooth breathing is encoded as `a<sm>`, for example.  Macra and brevia are treated as part of the vowel, and the breathings as secondary letters, so a long alpha with a smooth breathing would be encoded in this alphabet as `a<lo><sm>`.



### The default Greek alphabet

    #consonant# = bgdzqklmncprstfxy
    #vowel# = aeiouhw<isub>

    #letter# = #consonant# #vowel# #breathing#

    % Specific characters used in automatic formation of
    % stems of regular verbs:
    #alpha# = a
    #gamma# = g
    #epsilon# = e
    #eta# = h
    #theta# = q
    #kappa# = k
    #mu# = m
    #omicron# = o
    #sigma# = s
    #omega# = w
