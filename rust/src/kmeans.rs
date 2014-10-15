extern crate serialize;
extern crate time;

use std::io::File;
use std::collections::TreeMap;
use serialize::json;
use serialize::{Decoder, Decodable};
use time::now;

#[deriving(Show, PartialEq, PartialOrd, Clone)]
struct Point(f64, f64);

impl<E, D: Decoder<E>> Decodable<D, E> for Point {
  fn decode(d: &mut D) -> Result<Point, E> {
    d.read_tuple(|d, n| {
      if n != 2 { Err(d.error("invalid number of elements, need 2")) }
      else {
        d.read_tuple_arg(0, |d| d.read_f64()).and_then(|e1|
          d.read_tuple_arg(1, |d| d.read_f64()).map(|e2|
            Point(e1, e2)
          )
        )
      }
    })
  }
}

impl Add<Point, Point> for Point {
  fn add(&self, other: &Point) -> Point {
    let &Point(a, b) = self;
    let &Point(c, d) = other;

    Point(a + c, b + d)
  }
}

impl Sub<Point, Point> for Point {
  fn sub(&self, other: &Point) -> Point {
    let &Point(a, b) = self;
    let &Point(c, d) = other;

    Point(a - c, b - d)
  }
}

impl Eq for Point {}

impl Ord for Point {
  fn cmp(&self, other: &Point) -> Ordering {
    self.partial_cmp(other).unwrap_or(Equal)
  }
}

fn sq(x: f64) -> f64 { x * x }

fn norm(v: Point) -> f64 {
  let Point(x, y) = v;
  (sq(x) + sq(y)).sqrt()
}

fn dist(v: Point, w: Point) -> f64 { norm(v - w) }

fn avg(points: & Vec<Point>) -> Point {
  let Point(x, y) = points.iter().fold(Point(0.0, 0.0), |p, &q| p + q);
  let k = points.len() as f64;

  Point(x / k, y / k)
}

fn closest(x: Point, ys: & Vec<Point>) -> Point {
  let y0 = ys[0];
  let d0 = dist(y0, x);
  let (_, y) = ys.iter().fold((d0, y0),
    |(m, p), &q| {
      let d = dist(q, x);
      if d < m { (d, q) } else { (m, p) }
    }
  );
  y
}

fn clusters(xs: & Vec<Point>, centroids: & Vec<Point>) -> Vec<Vec<Point>> {
  let mut groups: TreeMap<Point, Vec<Point>> = TreeMap::new();

  for x in xs.iter() {
    let y = closest(*x, centroids);
    let should_insert = match groups.find_mut(&y) {
        Some(val) => {
          val.push(*x);
          false
        },
        None => true
    };
    if should_insert {
        groups.insert(y, vec![*x]);
    }
  }

  // groups.values().map(|x| *x.clone()).collect::<Vec<Vec<Point>>>()
  groups.into_iter().map(|(_, v)| v).collect::<Vec<Vec<Point>>>()
}

fn run(points: & Vec<Point>, n: uint, iters: uint) -> Vec<Vec<Point>> {
  let mut centroids: Vec<Point> = Vec::from_fn(n, |i| points[i]);

  for _ in range(0, iters) {
    centroids = clusters(points, & centroids).iter().map(|g| avg(g)).collect();
  }
  clusters(points, & centroids)
}

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