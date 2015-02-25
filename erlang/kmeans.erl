-module(kmeans).
-export([run/3]).
-compile(inline).

run(Xs, N, Iters) ->
    InitCentroids = lists:sublist(Xs, N),
    Step = fun(_, Centroids) ->
                   [average(X) || X <- clusters(Xs, Centroids)]
           end,
    FinalCentroids = lists:foldl(Step, InitCentroids, lists:seq(1, Iters)),
    clusters(Xs, FinalCentroids).

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

average(Q) ->
    divide(sum(Q),length(Q)).

sum(L) ->
    Add = fun(X, Acc) -> add(X, Acc) end,
    lists:foldl(Add, {0.0, 0.0}, L).

closest(P, Centroids) ->
    element(2, lists:min([{dist(P, C), C} || C <- Centroids])).

clusters(Xs, Centroids) ->
    groupBy(Xs, fun(X) -> closest(X, Centroids) end).

groupBy(L, Fn) ->
    Group = fun(X, Dict) ->
                    Add = fun(T) -> [X|T] end,
                    dict:update(Fn(X), Add, [X], Dict)
            end,
    Dict = lists:foldl(Group, dict:new(), L),
    [ V || {_,V} <- dict:to_list(Dict)].
