%% https://brilliant.org/courses/logic-deduction/the-rational-detective-3/riddles-of-order/4/

%% Marv encounters three more robots that are
%% all accusing one another of lying.

%% Remember,
%% * truthful robots only make true statements and
%% * lying robots only make false statements.

%% 1. Ada — "Bo is lying."
%% 2. Bo — "Ty is lying."
%% 3. Ty — "Ada and Bo are both lying."

:- use_module(library(clpb)).

answer(A, B, T) :-
    sat(A =:= ~B),
    sat(B =:= ~T),
    sat(T =:= ~A * ~B).

%?- answer(A, B, T).
%@ A = T, T = 0,
%@ B = 1.
