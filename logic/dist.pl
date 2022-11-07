%% search(T, H, X, R) - search X in list [H | T]. returns rest of the list after X
%% T - tail
%% H - head
%% X - search it
%% R - rest
search_(R, X, X, R).
search_([H | T], _, X, R) :- search_(T, H, X, R).

search(X, [Y | L], R) :- search_(L, Y, X, R).

dist(N, A, B, L) :- search(A, L, R), nth0(N, R, B).
dist(N, A, B, L) :- search(B, L, R), nth0(N, R, A).

:- begin_tests(dist).
test(d0no2, [fail]) :- dist(0, a, b, [a, c]).
test(d0direct2) :- dist(0, a, b, [a, b]).
test(d0inv2) :- dist(0, a, b, [b, a]).

test(d1no2, [fail]) :- dist(1, a, b, [a, b]).
test(d1no3, [fail]) :- dist(1, a, b, [a, b, c]).
test(d1no3a, [fail]) :- dist(1, a, b, [c, a, b]).
test(d1_3_direct) :- dist(1, a, b, [a, c, b]).
test(d1_3_inv) :- dist(1, a, b, [a, c, b]).
:- end_tests(dist).

next_to(A, B, L) :- dist(0, A, B, L).

:- begin_tests(next_to).
test(no2, [fail]) :- next_to(a, b, [a, c]).
test(no2a, [fail]) :- next_to(a, b, [c, a]).
test(direct2) :- next_to(a, b, [a, b]).
test(inv2) :- next_to(a, b, [b, a]).

test(direct3start) :- next_to(a, b, [a, b, c]).
test(inv3start) :- next_to(a, b, [b, a, c]).
test(direct3end) :- next_to(a, b, [c, a, b]).
test(inv3end) :- next_to(a, b, [c, b, a]).

test(direct4start) :- next_to(a, b, [a, b, c, d]).
test(inv4start) :- next_to(a, b, [b, a, c, d]).
test(direct4end) :- next_to(a, b, [c, d, a, b]).
test(inv4end) :- next_to(a, b, [c, d, b, a]).
test(direct4middle) :- next_to(a, b, [c, a, b, d]).
test(inv4middle) :- next_to(a, b, [c, b, a, d]).
:- end_tests(next_to).

%?- run_tests.
%% TODO: solve "Test succeeded with choicepoint" issue
%@  done
%@ % All 22 tests passed
%@ true.

%?- trace.
%@ true.

%?- L = [_, _, _], dist(1, a, b, L).

%?- L = [_, _, _, _], dist(1, a, b, L).
%@ L = [a, _, b, _] ;
%@ L = [_, a, _, b] ;
%@ L = [b, _, a, _] ;
%@ L = [_, b, _, a] ;
%@ false.
