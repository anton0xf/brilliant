next_to(A, B, [A, B | _]) :- !.
next_to(A, B, [B, A | _]) :- !.
next_to(A, B, [_ | L]) :- next_to(A, B, L).

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
%@ % PL-Unit: next_to .............. done
%@ % All 14 tests passed
%@ true.
