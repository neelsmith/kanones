This directory is for corpus-specific datasets (morphological lexica, inflectional rules, and definition of orthographic system).

Each corpus should have its dataset in a named directory with subdirectories laid out following the example in this repository's `datatemplate` directory.

You can build parsers from these datasets with the `scripts/parse.sc` script like this:


From a shell in the root of this repository, `sbt console`

Then

    :load scripts/parse.sc
    parse("DATASET_NAME")
    
