-module(kmeans).
-export([run/3]).

run(Xs, N, Iters) ->
	InitCentroids = lists:sublist(Xs, N),
	step(Iters, Xs, InitCentroids).

step(N, Xs, Centroids) ->
	NewCentroids = lists:map(fun(X) -> average(X) end, clusters(Xs, Centroids)),
	case N of
		0 -> NewCentroids;
		_ -> step((N-1), Xs, NewCentroids)
	end.

divide({Px,Py}, K) ->
	{Px/K, Py/K}.

add({Px1, Py1}, {Px2, Py2}) ->
	{(Px1+Px2), (Py1+Py2)}.

sub({Px1, Py1}, {Px2, Py2}) ->
	{(Px1-Px2), (Py1-Py2)}.

sq(X) -> 
	X*X.

modulus({Px, Py}) ->
	math:sqrt((sq(Px) + sq(Py))).

dist(P1, P2) ->
	modulus(sub(P1,P2)).

average([]) -> 0;
average(Q) -> divide(sum(Q),length(Q)).

sum([]) -> 0;
sum([H]) -> H;
sum([H | T]) -> add(H,sum(T)).

closest(P, [H | T]) ->
	First = {dist(P, H), H},
	closest(P, T, First).
closest(_, [], {_, Found}) -> Found;
closest(P, [H | T], {ActualWeight, ActualPoint}) ->
	NewWeight = dist(H, P),
	if
		NewWeight > ActualWeight -> closest(P, T, {ActualWeight, ActualPoint});
		true -> closest(P, T, {NewWeight, H})
	end.
	
clusters(Xs, Centroids) ->
	groupBy(Xs, fun(X) -> closest(X, Centroids) end).

groupBy(L, Fn) ->
	TableId = groupBy(L, Fn, dict:new()),
	values(dict:to_list(TableId)).

values([]) -> [];
values([{_, V} | T]) ->
	[V | values(T)].

groupBy([], _, TId) -> TId;
groupBy([H | T], Fn, TId) ->
	groupBy(T, Fn, dict:append(erlang:phash2(Fn(H)), H, TId)).