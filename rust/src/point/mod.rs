use serialize::{Decoder, Decodable};


#[deriving(Show, PartialEq, PartialOrd, Clone)]
pub struct Point(pub f64, pub f64);

fn sq(x: f64) -> f64 { x * x }

impl Point {
  pub fn norm(self: &Point) -> f64 {
    let Point(x, y) = *self;
    (sq(x) + sq(y)).sqrt()
  }
}

impl<E, D: Decoder<E>> Decodable<D, E> for Point {
  fn decode(d: &mut D) -> Result<Point, E> {
    d.read_tuple(2, |d| {
      d.read_tuple_arg(0, |d| d.read_f64()).and_then(|e1|
        d.read_tuple_arg(1, |d| d.read_f64()).map(|e2|
          Point(e1, e2)
        )
      )
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