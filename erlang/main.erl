-module(main).
-export([run/0]).

times() -> 100.

n() -> 10.
iters() -> 15.

run() ->
	{ok, Device} = file:open("../points.json", [read]),
	Line = io:get_line(Device, noLine),
	Tokens = string:tokens(Line, ",[]"),
	Xs = getFloats(Tokens),
	Times = lists:seq(1, times()),
	Sums = [getTime(Xs) || _ <- Times],
	Time = lists:sum(Sums)/length(Times),
	Time.

getTime(Xs) ->
	TimeBefore = get_timestamp(),
	kmeans:run(Xs, n(), iters()),
	TimeAfter = get_timestamp(),
	TimeAfter - TimeBefore.

getFloats([]) -> [];
getFloats([H1, H2 | T]) ->
	[F1 | _ ] = tuple_to_list(string:to_float(H1)),
	[F2 | _ ] = tuple_to_list(string:to_float(H2)),
	[{F1, F2} | getFloats(T)].

get_timestamp() ->
	{Mega, Sec, Micro} = os:timestamp(),
	(Mega*1000000 + Sec)*1000 + round(Micro/1000). 
