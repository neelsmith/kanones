---
title: Orthographic systems
layout: page
---


For a given morphological data set, the single file `orthography/alphabet.fst` defines the set of characters allowed in the data tables.  This file is written in SFST syntax:  if you want to customize your alphabet, you can refer to this [documentation of SFST](http://www.cis.uni-muenchen.de/~schmid/tools/SFST/data/SFST-Manual.pdf), but most frequently you will simply reuse an existing alphabet definition.


## Understanding the specification
The alphabet specification

<sm><ro>
<isub><lo><sh>


```shell
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
```
