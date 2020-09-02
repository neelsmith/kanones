---
title: Installation and configuration
layout: page
---


By default, the `sbt` build tasks look for configuration information in a file named `config.properties`:  alternatively, you can supply the name of a configuration file as a second parameter to the build task.  Its contents should include four settings, as illustrated in the default file;


    # Directory for datasets: relative or absolute path
    datadir = datasets
    # Location of necessary utilities in local system
    FSTCOMPILER = /usr/local/bin/fst-compiler
    FSTINFL = /usr/local/bin/fst-infl
    MAKE = /usr/bin/make


The `datadir` setting is a directory where kanónes will look for [morphological datasets](../datasets).  This valuye may be either an absolute file path or a path relative to the repository root. The default value is the relative directory `datasets`.
