-module(main).
-export([run/0]).

times() -> 100.

n() -> 10.
iters() -> 15.

run() ->
	Xs = read_points("../points.json"),
	Times = [getTime(Xs) || _ <- lists:seq(1, times())],
	Time = lists:sum(Times)/times(),
	Time.

read_points(FileName) ->
    {ok, Data} = file:read_file(FileName),
    [<<>>|Bins] = re:split(Data, "[][,]+", [trim]),
    points([binary_to_float(X) || X <- Bins]).

getTime(Xs) ->
    Ms = element(1, timer:tc(kmeans, run, [Xs, n(), iters()]))/1000,
%    io:write(Ms), io:nl(),
    Ms.

points([]) -> [];
points([H1, H2 | T]) ->
	[{H1, H2} | points(T)].
