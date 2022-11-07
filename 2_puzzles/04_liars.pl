%% https://brilliant.org/courses/logic-deduction/the-rational-detective-3/riddles-of-order/6/

%% 1. Dot — "None of us are lying."
%% 2. Mig — "At least one of us is lying."
%% 3. Loy — "At least two of us are lying."
%% 4. Var — "Loy is lying."

answer(X) :-
    X = [D, M, L, V],
    sat(D =:= *(X)),
    sat(M =:= card([0, 1, 2, 3], X)),
    sat(L =:= card([0, 1, 2], X)),
    sat(V =:= ~L).

%?- answer(X).
%@ X = [0, 1, 1, 0].
