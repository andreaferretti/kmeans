open Core.Std
open Point

let min_by f xs =
  let Some h = List.hd xs in
  let m = f h in
  let (_, min_elt) = List.fold xs ~init:(m, h)
    ~f:(fun (a, x) y -> let b = f y in (if b < a then (b, y) else (a, x))) in
  min_elt

let closest p qs = min_by (dist p) qs

let sum qs = List.fold qs ~init:{x = 0.0; y = 0.0} ~f:( ++ )

let average qs = (sum qs) // (qs |> List.length |> Float.of_int)

let group_by xs f =
  let table = Hashtbl.Poly.create () in
  List.iter xs (fun x ->
    let y = f x in
    match Hashtbl.find table y with
      | Some l -> Hashtbl.replace table ~key:y ~data:(x :: l)
      | None -> Hashtbl.replace table ~key:y ~data:[x]);
  table

let clusters xs centroids =
  group_by xs (fun p -> closest p centroids)
  |> Hashtbl.data

let run points iters n =
  let centroids = ref (List.take points n) in
  let new_clusters = ref (clusters points !centroids) in
  for i = 1 to iters do
    centroids := List.map !new_clusters average;
    new_clusters := clusters points !centroids;
  done;
  !new_clusters