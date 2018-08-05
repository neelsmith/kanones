
## Contents

This directory contains a `kanónes` dataset.

It is currently in development.  It is planned to include one example of each rule type, one example of each stem type, and one example of each irregular type.

The file `wordlist.txt` will include a minimal set of words to test each rule+stem combination.

## Testing

**Build and testing the parser**. From an `sbt console` in the root `tabulae` directory,

    :load scripts/parse.sc
    compile("analytical-types")
    parse("datasets/analytical-types/wordlist.txt")
