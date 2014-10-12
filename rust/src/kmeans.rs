fn sq(x: f64) -> f64 { x * x }

fn dist(v: [f64, ..2], w: [f64, ..2]) -> f64 {
  (sq(v[0] - w[0]) + sq(v[1] - w[1])).sqrt()
}


fn main() {
  println!("Hello, world");
  let s = "Hello";
  let v = [1.0, 2.0];
  let w = [2.5, 3.12];
  for c in s.chars() {
      println!("{}", c);
  }
  println!("The distance between v and w is {}", dist(v, w));
}
