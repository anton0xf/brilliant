dist(N, A, B, [A | L]) :- nth0(N, L, B), !.
dist(N, A, B, [B | L]) :- nth0(N, L, A), !.
dist(N, A, B, [_ | L]) :- dist(N, A, B, L).

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
%@ % PL-Unit: dist ........ done
%@ % PL-Unit: next_to .............. done
%@ % All 22 tests passed
%@ true.

