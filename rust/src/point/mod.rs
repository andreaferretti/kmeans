use serialize::{Decoder, Decodable};
use std::hash::Hash;
use std::hash::sip::SipState;
use std::mem;
use std::num::Float;
use std::ops::{Add,Sub};

#[derive(Show, PartialEq, PartialOrd, Copy, Clone)]
pub struct Point(pub f64, pub f64);

fn sq(x: f64) -> f64 { x * x }

impl Point {
  pub fn norm(self: &Point) -> f64 {
    let Point(x, y) = *self;
    (sq(x) + sq(y)).sqrt()
  }
}

impl Hash for Point {
    fn hash(&self, state: &mut SipState) {
        // Perform a bit-wise transform, relying on the fact that we
        // are never Infinity or NaN
        let Point(x, y) = *self;
        let x: u64 = unsafe { mem::transmute(x) };
        let y: u64 = unsafe { mem::transmute(y) };
        x.hash(state);
        y.hash(state);
    }
}

impl Add<Point, Point> for Point {
    fn add(self, other: Point) -> Point {
        let Point(a, b) = self;
        let Point(c, d) = other;

        Point(a + c, b + d)
    }
}

impl Sub<Point, Point> for Point {
    fn sub(self, other: Point) -> Point {
        let Point(a, b) = self;
        let Point(c, d) = other;

        Point(a - c, b - d)
    }
}

impl Eq for Point {}

impl<E, D: Decoder<E>> Decodable<D, E> for Point {
    fn decode(d: &mut D) -> Result<Point, E> {
        d.read_tuple(2, |d| {
            d.read_tuple_arg(0, |d| d.read_f64()).and_then(|e1| {
                d.read_tuple_arg(1, |d| d.read_f64()).map(|e2| {
                    Point(e1, e2)
                })
            })
        })
    }
}
