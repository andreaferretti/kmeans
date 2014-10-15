extern crate serialize;

use serialize::json;
use serialize::{Decoder, Decodable};
use std::io::File;
use std::collections::TreeMap;

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
  let mut groups: TreeMap<Point, Box<Vec<Point>>> = TreeMap::new();

  // for x in xs.iter() {
  //   let y = closest(*x, centroids);
  //   match groups.find(&y) {
  //     Some(ref mut val) => val.push(*x),
  //     None => {
  //       groups.insert(y, box vec![*x]);
  //     },
  //   }
  // }

  groups.values().map(|x| *x.clone()).collect::<Vec<Vec<Point>>>()
}


fn main() {
  let path = "../points.json".to_string();
  let contents = File::open(&Path::new(path.as_slice())).read_to_string().unwrap();
  // let vec_points: Vec<Vec<f64>> = json::decode(contents.as_slice()).unwrap();
  // let points: & Vec<Point> = & vec_points.into_iter().map(|v| Point(v[0], v[1])).collect();
  let points: Vec<Point> = json::decode(contents.as_slice()).unwrap();
  let n: uint = 10;
  let iters: uint = 15;
  let mut centroids: Vec<Point> = Vec::from_fn(n, |i| points[i]);

  for i in range(0, iters) {
    centroids = clusters(& points, & centroids).iter().map(|g| avg(g)).collect();
  }
  let result = clusters(& points, & centroids);

  println!("The center is {}", avg(& points));
}
