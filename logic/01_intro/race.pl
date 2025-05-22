%% https://brilliant.org/courses/logic-deduction/introduction-68/harder-challenges/5/

%% In the next race, five robots compete. They report these:
%% c1 * Marv finishes either second or fourth.
%% c2 * Rae finishes either first or fifth.
%% c3 * Lex finishes either third or fourth.
%% c4 * Ty finishes either first or second.
%% c5 * Mig finishes either third or fifth.

%% How many different orderings are possible?

c1([_, marv, _, _, _]). % second
c1([_, _, _, marv, _]). % forth

c2([rae, _, _, _, _]).
c2([_, _, _, _, rae]).

c3([_, _, lex, _, _]).
c3([_, _, _, lex, _]).

c4([ty, _, _, _, _]).
c4([_, ty, _, _, _]).

c5([_, _, mig, _, _]).
c5([_, _, _, _, mig]).


race(L) :- c1(L), c2(L), c3(L), c4(L), c5(L).

%?- race(X).
%@ X = [ty, marv, mig, lex, rae] ;
%@ X = [rae, ty, lex, marv, mig] ;
%@ false.
