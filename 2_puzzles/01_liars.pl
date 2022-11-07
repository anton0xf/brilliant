%% https://brilliant.org/courses/logic-deduction/the-rational-detective-3/riddles-of-order/3/

%% There are now two types of robots in the theme park:
%% * Truthful robots only make true statements.
%% * Lying robots only make false statements.

%% 1. Zod — "We are both truthful."
%% 2. Jenk — "At least one of us is lying."

:- use_module(library(clpb)).

answer(Z, J) :-
    sat(Z =:= Z * J),
    sat(J =:= ~Z + ~J).

%?- answer(Z, J).
%@ Z = 0,
%@ J = 1.
