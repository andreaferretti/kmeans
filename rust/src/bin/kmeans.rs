extern crate serialize;
extern crate time;
extern crate kmeans;

use std::io::File;
use serialize::json;
use time::now;
use kmeans::point::Point;
use kmeans::algo::run;


fn benchmark(points: & Vec<Point>, times: uint) -> f64 {
  let start = now().to_timespec();
  for _ in range(0, times) {
    run(points, 10, 15);
  }
  let end = now().to_timespec();
  ((end - start).num_milliseconds() as f64) / (times as f64)
}

fn main() {
  let contents = File::open(&Path::new("../points.json".as_slice())).read_to_string().unwrap();
  let points: Vec<Point> = json::decode(contents.as_slice()).unwrap();
  let iterations: uint = 100;

  println!("The average time is {}", benchmark(& points, iterations));
}