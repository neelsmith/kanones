
#include "/Users/nsmith/repos/greek/kanones/parsers/vienna_lit/symbols.fst"


%%%
%%% Adjusting stem  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%
$stems_acceptors$ = "</data/repos/greek/kanones/parsers/vienna_lit/acceptors/verbstems.a>"

%%%
%%% Adjusting augment  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%
ALPHABET = [#editorial# #urntag# #urnchar# <verb> #morphtag# #stemtype#  #separator# #accent# #letter# #diacritic#  #breathing# \. #stemchars# ]

#augmenttense# = <aor><impft><plupft>


#=ltr# = #consonant#

$aug$ = { [#=ltr#]}:{e<sm>[#=ltr#]} ^-> (<#>__ [#stemchars#]+<verb>[#verbclass#]\:\:[#verbclass#]<verb>[#stemchars#]+[#person#][#number#][#augmenttense#]<indic>[#voice#]<u>[#urnchar#]+[#period#][#urnchar#]+</u>)

%%%
%%% The URN squasher for verbs %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%
$=verbclass$ = [#verbclass#]
$squashverburn$ = <u>[#urnchar#]:<>+\.:<>[#urnchar#]:<>+</u><u>[#urnchar#]:<>+\.:<>[#urnchar#]:<>+</u>[#stemchars#]+<verb>$=verbclass$\:\:$=verbclass$ <verb>[#stemchars#]* [#person#] [#number#] [#tense#] [#mood#] [#voice#]<u>[#urnchar#]:<>+\.:<>[#urnchar#]:<>+</u>

$stems_acceptors$ || $aug$ || $squashverburn$
