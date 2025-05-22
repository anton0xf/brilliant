%% https://brilliant.org/courses/logic-deduction/introduction-68/strategic-deductions-2/9/

%% 1. chest A has more coins than the chest to its right.
%% 2. chest B has more coins than the chest to ist left.
%% 3. chest C is not in one of the middle places.
%% 4. chest D has more coins than chest A.

in_a_row(A, B, [A, B | _]).
in_a_row(A, B, [_ | L]) :- in_a_row(A, B, L).

more_coins(X, Y, O) :- in_a_row(Y, X, O).

%% L - list of chests in order
%% O - list of chests sorted by increasing numer of coins
c1(L, O) :- in_a_row(a, X, L), more_coins(a, X, O).
c2(L, O) :- in_a_row(X, b, L), more_coins(b, X, O).

% c3(L)
c3([c, _, _, _]).
c3([_, _, _, c]).

c4(O) :- more_coins(d, a, O).

answer(L, O) :-
    L = [_, _, _, _],
    O = [_, _, _, _],
    c1(L, O), c2(L, O), c3(L), c4(O),
    permutation(L, [a, b, c, d]),
    permutation(O, [a, b, c, d]).

%?- answer(L, O).
%@ L = [d, b, a, c],
%@ O = [c, a, d, b] ;
%@ false.
