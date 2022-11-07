in_a_row(A, B, [A, B | _]) :- !.
in_a_row(A, B, [_ | L]) :- in_a_row(A, B, L).

:- begin_tests(in_a_row).
test(no2, [fail]) :- in_a_row(a, b, [a, c]).
test(no2a, [fail]) :- in_a_row(a, b, [c, a]).
test(direct2) :- in_a_row(a, b, [a, b]).
test(no_inv2, [fail]) :- in_a_row(a, b, [b, a]).

test(direct3start) :- in_a_row(a, b, [a, b, c]).
test(no_inv3start, [fail]) :- in_a_row(a, b, [b, a, c]).
test(direct3end) :- in_a_row(a, b, [c, a, b]).
test(no_inv3end, [fail]) :- in_a_row(a, b, [c, b, a]).

test(direct4start) :- in_a_row(a, b, [a, b, c, d]).
test(no_inv4start, [fail]) :- in_a_row(a, b, [b, a, c, d]).
test(direct4end) :- in_a_row(a, b, [c, d, a, b]).
test(no_inv4end, [fail]) :- in_a_row(a, b, [c, d, b, a]).
test(direct4middle) :- in_a_row(a, b, [c, a, b, d]).
test(no_inv4middle, [fail]) :- in_a_row(a, b, [c, b, a, d]).
:- end_tests(in_a_row).

not_next(A, B, L) :- in_a_row(A, N, L), N \= B.

:- begin_tests(not_next).
test(next1, [fail]) :- not_next(a, b, [a, b, c, d, e, f]).
test(next2, [fail]) :- not_next(b, c, [a, b, c, d, e, f]).
test(next3, [fail]) :- not_next(d, e, [a, b, c, d, e, f]).
test(next4, [fail]) :- not_next(e, f, [a, b, c, d, e, f]).

test(prev1) :- not_next(b, a, [a, b, c, d, e, f]).
test(prev2) :- not_next(e, d, [a, b, c, d, e, f]).

test(other1) :- not_next(a, c, [a, b, c, d, e, f]).
test(other3) :- not_next(a, f, [a, b, c, d, e, f]).
test(other1) :- not_next(c, f, [a, b, c, d, e, f]).
:- end_tests(not_next).

not_next_to(A, B, L) :- not_next(A, B, L), not_next(B, A, L).

%?- run_tests.
%@ % PL-Unit: in_a_row .............. done
%@ % PL-Unit: not_next ......... done
%@ % All 23 tests passed
%@ true.
