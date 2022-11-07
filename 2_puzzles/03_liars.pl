%% https://brilliant.org/courses/logic-deduction/the-rational-detective-3/riddles-of-order/5/

%% 1. Gom — "Yan is truthful."
%% 2. Kit — "At least two of us are truthful."
%% 3. Yan — "Kit is lying."
%% 4. Ash — Gom is lying, or Kit is lying.

answer(L) :-
    L = [G, K, Y, A],
    sat(G =:= Y),
    sat(K =:= card([2, 3, 4], L)),
    sat(Y =:= ~K),
    sat(A =:= ~G + ~K).

%?- answer(L).
%@ L = [0, 1, 0, 1].
