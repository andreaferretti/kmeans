use rustc_serialize::{Decoder, Decodable};
use std::hash::{Hash, Hasher, Writer };
use std::mem;
use std::num::Float;
use std::ops::{Add,Sub};

#[derive(Show, PartialEq, PartialOrd, Copy, Clone)]
pub struct Point(pub f64, pub f64);

fn sq(x: f64) -> f64 { x * x }

impl Point {
    pub fn norm(self: &Point) -> f64 {
        (sq(self.0) + sq(self.1)).sqrt()
    }
}

impl<H: Hasher + Writer> Hash<H> for Point {
    fn hash(&self, state: &mut H) {
        // Perform a bit-wise transform, relying on the fact that we
        // are never Infinity or NaN
        let Point(x, y) = *self;
        let x: u64 = unsafe { mem::transmute(x) };
        let y: u64 = unsafe { mem::transmute(y) };
        x.hash(state);
        y.hash(state);
    }
}

impl Add for Point {
    type Output = Point;

    fn add(self, other: Point) -> Point {
        Point(self.0 + other.0, self.1 + other.1)
    }
}

impl Sub for Point {
    type Output = Point;

    fn sub(self, other: Point) -> Point {
        Point(self.0 - other.0, self.1 - other.1)
    }
}

impl Eq for Point {}

impl Decodable for Point {
    fn decode<D: Decoder>(d: &mut D) -> Result<Point, D::Error> {
        d.read_tuple(2, |d| {
            d.read_tuple_arg(0, |d| d.read_f64()).and_then(|e1| {
                d.read_tuple_arg(1, |d| d.read_f64()).map(|e2| {
                    Point(e1, e2)
                })
            })
        })
    }
}
