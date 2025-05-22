%% https://brilliant.org/courses/logic-deduction/the-rational-detective-3/riddles-of-order/8/

%% These next 4 robots have the ability to dispense the coins that
%% theme park visitors receive as prizes. Here’s what Marv knows:

%% 1. Each robot can dispense a unique coin.
%% 2. Only one of the robots is labeled correctly.
%% 3. The “Silver” robot dispenses jade coins.
%% 4. The “Gold” robot is incorrectly labeled.

:- use_module(library(clpb)).

answer(L) :-
    L = [G, S, C, J],
    M = [g, s, c, j],
    permutation(L, M), % 1
    sat(
