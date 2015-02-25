var _ = require("lodash");
var fs = require("fs");

function sq(x) { return x * x }

function dist(x, y) {
  return Math.sqrt(sq(x[0] - y[0]) + sq(x[1] - y[1]));
}

function closest(x, choices) {
  return _.min(choices, function(y) { return dist(x, y); });
}

function clusters(xs, centroids) {
  return _(xs)
    .groupBy(function (x) { return closest(x, centroids); })
    .values()
    .value();
}

function vsum(v, w) {
  return _.map(v, function(x, i) { return x + w[i]; });
}

function average(xs) {
  var total = _.reduce(xs, vsum);
  var l = xs.length;

  return _.map(total, function(t) { return t / l; });
}

function run(xs, n, iters) {
  var centroids = _.take(xs, n);
  for (var i = 0; i < iters; i++) {
    centroids = _.map(clusters(xs, centroids), average);
  }
  return clusters(xs, centroids);
}


var points = JSON.parse(fs.readFileSync("../points.json"));
var iterations = 100;
var n = 10;
var start = (new Date()).getTime();
for (var i = 0; i < iterations; i++) {
  run(points, n, 15);
}
var time = ((new Date()).getTime() - start) / iterations;

console.log("Running " + iterations + " iterations as required " + time + " ms");
