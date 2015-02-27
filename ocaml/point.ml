open Core.Std

type t = { x: float; y: float }

let ( ++ ) { x = x1; y = y1 } { x = x2; y = y2 } = { x = x1 +. x2; y = y1 +. y2 }

let ( -- ) { x = x1; y = y1 } { x = x2; y = y2 } = { x = x1 -. x2; y = y1 -. y2 }

let ( // ) { x; y } k = { x = x /. k; y = y /. k }

let norm { x; y } = sqrt (x *. x +. y *. y)

let dist x y = norm (x -- y)