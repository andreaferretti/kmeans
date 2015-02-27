open Core.Std

let read_point json =
  let open Yojson.Basic.Util in
  let open Point in
  let [x; y] = List.map (json |> to_list) ~f:to_float in
  { x; y }

let read_points path =
  let json = Yojson.Basic.from_file path in
  let open Yojson.Basic.Util in
  let values = json |> to_list in
  List.map values ~f:read_point

let () =
  let runs = 100 in
  let points = read_points "../points.json" in
  let start = Time.now() in
  for i = 1 to runs do
    ignore(Kmeans.run points 15 10);
  done;
  let finish = Time.now() in
  let milliseconds = (Time.diff finish start) |> Time.Span.to_ms in
  Printf.printf "We made 100 iterations with an average of %f ms\n" (milliseconds /. (Float.of_int runs))

(* corebuild -pkg yojson main.native *)