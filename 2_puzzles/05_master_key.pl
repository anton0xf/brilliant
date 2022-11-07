%% https://brilliant.org/courses/logic-deduction/the-rational-detective-3/riddles-of-order/7/

%% One of Marv’s tasks this morning was to retrieve the master key to the park
%% and deliver it to HQ. That’s been made harder by the lying robots.

%% He knows that exactly one of these 3 vault robots has the master key
%% to the park. Only one of the robots is telling the truth.

%% 1. Ig — "I have the master key."
%% 2. Ott — "I do not have the master key."
%% 3. Jett — "Ig doesn't have the master key."

answer(T, M) :- % Trust, Master
    T = [I, O, J],
    sat(card([1], T)),
    M = [IM, OM, JM],
    sat(card([1], M)),
    sat(I =:= IM), %1
    sat(O =:= ~OM), %2
    sat(J =:= ~IM). %3

%?- answer(T, M).
%@ T = [0, 0, 1],
%@ M = [0, 1, 0].
