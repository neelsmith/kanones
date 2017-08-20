%% Experiment with augment patterns.
%

#include "/Users/nsmith/repos/greek/kanones/parsers/vienna_lit/symbols.fst"

%% This is what a morph db entry for a verb looks like:
% <u>smyth.n76586_0</u><u>lexent.n76586</u><#>paideu<verb><w_regular>::<w_pp1><verb>w<1st><sg><pres><indic><act><u>verbinfl.w_pr_indic1</u>


%%% 1. HAND CRAFTED DATA SET FOR DEBUGGING: %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
$lex$ = <u>tinytiny\.ex\_0</u><u>lexent\.n76586</u><#>paideu<verb><w_regular> |\
<u>vienna\.n5\_0</u><u>lexent\.n5</u><#>gra<sh>y<verb><w_pp3>

$end$ = <w_regular><verb>a<1st><sg><aor><indic><act><u>verbinfl\.w_pr_indic1</u> |\
<w_pp3><verb>e<3rd><sg><aor><indic><act><u>verbinfl\.w\_pp3\_aor\_indic3b</u>

$db$ = $lex$ $separator$+ $end$


%%% 2. THE URN SQUASHER FOR VERBS: %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
$=verbclass$ = [#verbclass#]
$squashverburn$ = <u>[#urnchar#]:<>+\.:<>[#urnchar#]:<>+</u><u>[#urnchar#]:<>+\.:<>[#urnchar#]:<>+</u>[#stemchars#]+<verb>$=verbclass$\:\:$=verbclass$ <verb>[#stemchars#]* [#person#] [#number#] [#tense#] [#mood#] [#voice#]<u>[#urnchar#]:<>+\.:<>[#urnchar#]:<>+</u>


%%% 3. STRIPPING OFF ANALYSIS SYMBOLS: %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

#analysissymbol# = #editorial# #urntag# <noun><verb><indecl><ptcpl><infin><vadj><adj><adv> #morphtag# #stemtype#  #separator# #accent#
#surfacesymbol# = #letter# #diacritic#  #breathing#
ALPHABET = [#surfacesymbol#] [#analysissymbol#]:<>
$stripsym$ = .+



%%% Adjusting stem and augment: %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

$stems_acceptors$ = "</data/repos/greek/kanones/parsers/vienna_lit/acceptors/verbstems.a>"

ALPHABET = [#editorial# #urntag# #urnchar# <verb> #morphtag# #stemtype#  #separator# #accent# #letter# #diacritic#  #breathing# \. #stemchars# ]

#augmenttense# = <aor><impft><plupft>


#=ltr# = #consonant#

$aug$ = { [#=ltr#]}:{e<sm>[#=ltr#]} ^-> (<#>__ [#stemchars#]+<verb>[#verbclass#]\:\:[#verbclass#]<verb>[#stemchars#]+[#person#][#number#][#augmenttense#]<indic>[#voice#]<u>[#urnchar#]+[#period#][#urnchar#]+</u>)

$db$ ||  $stems_acceptors$  || $aug$ || $squashverburn$ || $stripsym$
