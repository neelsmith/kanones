%% rawmorph.fst :
% unmodified combinations of stems+inflectional patterns
%
% All symbols used in the FST:
#include "@workdir@symbols.fst"
%
% Dynamically loaded lexica of stems:
$stems$ = @lexica@
%
% Dynamically loaded inflectional rules:
$ends$ = "<@workdir@inflection.a>"


% Morphology data is the crossing of stems and endings:
$morph$ = $stems$  <div> $ends$

$morph$
