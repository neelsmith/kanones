% phonology.fst
%
% Definitions of all legal symbols in stem files (lexica) except for
% stem types (defined in stemtypes.fst)

%
% Basic alphabet specific to this orthographic system:
#include "@workdir@symbols/alphabet.fst"


#diaeresis# = \+
#accent# = \/<circ>
#diacritic# = #diaeresis# #accent#
#breathing# = <sm><ro>

% Accents should only be included in entries for
% irregular forms!

#character# = #letter# #diacritic# #accent# #breathing#
$character$ = [#character#]

% Additional editorial symbols used in stem files:
%#vowelquant# = <lo><sh>
#vowelquant# = _^
#morpheme# = <#>
#persistacc# = <stemultacc><stempenacc><inflacc><irregacc>
#editorial# = #morpheme# #persistacc#  #vowelquant#
% Transducer for persistent accent for convenenient
% composition
$persistacc$ = [#persistacc#]

% All valid chars used in stem file:
#stemchars# = #character# #editorial#
#inmorpheme# = #character# #vowelquant# #persistacc#
