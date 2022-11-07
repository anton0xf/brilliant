%% https://brilliant.org/courses/logic-deduction/introduction-68/strategic-deductions-2/8/

%% How can the robots be seated to satisfy these requests?
%% 1. There is exactly one seat between Rae and Mig.
%% 2. There are either 2 or 3 seats between Lex and Knuck.
%% 3. Neither Knuck nor Mig is next to the empty seat.

:- [dist, in_a_row].

c1(L) :- dist(1, rae, mig, L).

c2(L) :- dist(2, lex, knuck, L).
c2(L) :- dist(3, lex, knuck, L).

c3(L) :- not_next_to(knuck, empty, L),
         not_next_to(mig, empty, L).

answer(L) :-
    L = [_, _, _, _, _],
    member(empty, L),
    c1(L), c2(L), c3(L).

%?- answer(L).
%@ L = [mig, knuck, rae, empty, lex] ;
%@ false.
