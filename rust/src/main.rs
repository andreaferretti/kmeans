extern crate time;
extern crate kmeans;
extern crate rustc_serialize;

use std::path::Path;
use std::fs::File;
use std::io::Read;
use rustc_serialize::json;

use time::now;
use kmeans::point::Point;
use kmeans::algo::run;

fn benchmark(points: &[Point], times: i32) -> f64 {
    let start = now().to_timespec();

    for _ in (0 .. times) {
        run(points, 10, 15);
    }

    let end = now().to_timespec();

    ((end - start).num_milliseconds() as f64) / (times as f64)
}

fn main() {
    let mut file = File::open(&Path::new("../points.json")).unwrap();
    let mut buffer: Vec<u8> = vec!();
    let _ = file.read_to_end(&mut buffer).unwrap();
    let filestr = String::from_utf8(buffer).unwrap();

    let points: Vec<Point> = json::decode(&filestr).unwrap();
    let iterations = 100;

    println!("The average time is {}", benchmark(&points, iterations));
}
