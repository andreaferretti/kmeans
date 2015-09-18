#include <string>
#include <algorithm>
#include <fstream>
#include <iostream>
#include <iomanip>
#include <vector>
#include <stdexcept>
#include <chrono>
#include "json11/json11.hpp"
#include "Point.h"
#include "kmeans.h"

using std::string;
using std::cout;
using std::endl;
using std::vector;
using std::domain_error;
using std::ifstream;
using std::chrono::steady_clock;
using json11::Json;

Point read_point(const Json& json) {
  if (json.is_array()) {
    auto coords = json.array_items();
    return Point(coords[0].number_value(), coords[1].number_value());
  }
  throw domain_error("Invalid JSON");
}

vector<Point> read_points(const string& path) {
  if (ifstream infile{path}) {
    const string contents{std::istreambuf_iterator<char>(infile), {}};
    string error;

    auto json = Json::parse(contents, error);
    auto items = json.array_items();
    vector<Point> result;
    transform(items.begin(), items.end(), back_inserter(result), read_point);

    return result;
  }
  throw domain_error("Invalid JSON");
}

int main() {
  const int n = 10;
  const int iters = 15;
  const int repeat = 100;
  auto points = read_points("/home/papillon/esperimenti/kmeans/points.json");

  steady_clock::time_point begin = steady_clock::now();
  for (int i = 0; i < repeat; i++) {
    auto centroids = kmeans(points, n, iters);
  }
  steady_clock::time_point end = steady_clock::now();
  int millis = std::chrono::duration_cast<std::chrono::milliseconds>(end - begin).count();
  int average = millis / repeat;

  cout << "Average running time = " << average << " ms" << std::endl;
  return 0;
}