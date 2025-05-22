%% https://brilliant.org/courses/logic-deduction/advanced-knights-and-knaves-old-title/practice/logic-standalone-151/

%% What is the greatest possible number of androids? 
%% Humans tell the truth, and androids lie.

:- use_module(library(clpb)).

%% F - Fitz is a human, ~F - Fitz is an android
solution(F, J, H, K, E, C) :-
    sat(F =:= J), % Fitz: Jing is a human
    sat(J =:= ~H), % Jing: Hal is an android
    sat(H =:= K), % Hal: Kara is a human
    sat(K =:= ~E), % Kara: Esther is an android
    sat(E =:= C). % Esther: Chris is a human

%?- solution(F, J, H, K, E, C).
%@ F = J, J = E, E = C,
%@ H = K,
%@ sat(C=\=K).

%?- solution(F, J, H, K, E, C), labeling([F, J, H, K, E, C]).
%@ F = J, J = E, E = C, C = 0,
%@ H = K, K = 1 ;
%@ F = J, J = E, E = C, C = 1,
%@ H = K, K = 0.


answer(N) :- L = [F, J, H, K, E, C],
             solution(F, J, H, K, E, C),
             integer(N),
             sat(card([N], L)).

%?- answer(N).
%@ false.

%?- solution(F, J, H, K, E, C), sat(card(NS, [F, J, H, K, E, C])). 
%@ ERROR: Arguments are not sufficiently instantiated

%% TODO why card cannot count?
