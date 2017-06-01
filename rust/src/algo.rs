use std::collections::HashMap;
use std::collections::hash_map::Entry::{Occupied, Vacant};

use point::Point;

fn dist(v: Point, w: Point) -> f64 {
    (v - w).norm()
}

fn avg(points: &[Point]) -> Point {
    let Point(x, y) = points.iter().fold(Point(0.0, 0.0), |p, &q| p + q);
    let k = points.len() as f64;

    Point(x / k, y / k)
}

fn closest(x: Point, ys: &[Point]) -> Point {
    let y0 = ys[0];
    let d0 = dist(y0, x);
    let (_, y) = ys.iter()
        .fold((d0, y0), |(m, p), &q| {
            let d = dist(q, x);
            if d < m { (d, q) } else { (m, p) }
        });
    y
}

fn clusters(xs: &[Point], centroids: &[Point]) -> Vec<Vec<Point>> {
    let mut groups: HashMap<Point, Vec<Point>> = HashMap::new();

    for x in xs.iter() {
        let y = closest(*x, centroids);

        // Notable change: avoid double hash lookups
        match groups.entry(y) {
            Occupied(entry) => entry.into_mut().push(*x),
            Vacant(entry) => {
                entry.insert(vec![*x]);
                ()
            }
        }
    }

    groups
        .into_iter()
        .map(|(_, v)| v)
        .collect::<Vec<Vec<Point>>>()
}

pub fn run(points: &[Point], n: u32, iters: u32) -> Vec<Vec<Point>> {
    let mut centroids: Vec<Point> = points.iter().take(n as usize).cloned().collect();

    for _ in 0..iters {
        centroids = clusters(points, &centroids)
            .iter()
            .map(|g| avg(&g))
            .collect();
    }
    clusters(points, &centroids)
}
