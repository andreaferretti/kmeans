#![feature(core)]
#![feature(io)]
#![feature(path)]
#![feature(std_misc)]

extern crate "rustc-serialize" as rustc_serialize;
extern crate time;
extern crate kmeans;

use std::old_io::File;
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
    let contents = File::open(&Path::new("../points.json".as_slice())).read_to_string().unwrap();

    let points: Vec<Point> = json::decode(&contents[]).unwrap();
    let iterations: i32 = 100;

    println!("The average time is {}", benchmark(&points[], iterations));
}
