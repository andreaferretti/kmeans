use std::collections::TreeMap;
use point::Point;


fn dist(v: Point, w: Point) -> f64 { (v - w).norm() }

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
    let should_insert = match groups.get_mut(&y) {
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

  groups.into_iter().map(|(_, v)| v).collect::<Vec<Vec<Point>>>()
}

pub fn run(points: & Vec<Point>, n: uint, iters: uint) -> Vec<Vec<Point>> {
  let mut centroids: Vec<Point> = Vec::from_fn(n, |i| points[i]);

  for _ in range(0, iters) {
    centroids = clusters(points, & centroids).iter().map(|g| avg(g)).collect();
  }
  clusters(points, & centroids)
}