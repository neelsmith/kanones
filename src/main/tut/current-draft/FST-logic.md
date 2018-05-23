---
title: FST logic
layout: page
---


The final parsing transducer is actually created by a chain of smaller transducers.  In broad outline, they do the following:

1. combine all lexical entries ("stems") with all inflectional rules (that is, generate the cross product of stems and rules)
2. pass to a transducer that accepts only pairings of stems and rules belonging to the same  stemtype ([defined in `symbols/stemtypes.fst`](../FST-symbols))
3. categorize all symbols as either "surface" symbols, or "analytical" symbols, and allow only one category to pass through.




The transducers that do this work are organized in the following directories:


- inflection
- [acceptors](Acceptors)


The definition of the corpus-specific set of symbols used by the FST is described [here](FST-symbols).
