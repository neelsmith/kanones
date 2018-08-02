
## Contents

This directory contains a `kanónes` dataset.

It is currently in development.  It is planned to include one example of each rule type, one example of each stem type, and one example of each irregular type.

The file `wordlist.txt` will include a minimal set of words to test each rule+stem combination.

## Testing

**Build the parser**. From an `sbt` console in the root `tabulae` directory:

    fst analytical-types

**Analyze the word list**. From a bash shell in the root `tabulae` directory:


    fst-infl parsers/analytical-types/latin.a datasets/analytical-types/wordlist.txt
