#include <numeric>
#include <limits>
#include <map>
#include <vector>
#include "kmeans.h"

using std::vector;
using std::map;

Point average(const vector<Point>& v) {
  return accumulate(v.begin(), v.end(), Point(0.0, 0.0)) / v.size();
}

Point closest(Point p, const vector<Point>& centroids) {
  double minDist = std::numeric_limits<double>::max();
  Point result;

  for (const auto& c : centroids) {
    auto d = dist(p, c);
    if (d < minDist) {
      minDist = d;
      result = c;
    }
  }
  return result;
}

map<Point, vector<Point>> group_by(const vector<Point>& points, const vector<Point>& centroids) {
  map<Point, vector<Point>> groups;
  for (const auto& p : points) {
    groups[closest(p, centroids)].push_back(p);
  }

  return groups;
}

void update_centroids(const vector<Point>& points, vector<Point>& centroids) {
  auto groups = group_by(points, centroids);
  int i = 0;
  for (const auto& g : groups) {
    centroids[i] = average(g.second);
    ++i;
  }
}

vector<Point> kmeans(const vector<Point>& points, int n, int iterations) {
  vector<Point> centroids(points.begin(), points.begin() + n);
  for (int i = 0; i < iterations; i++) {
    update_centroids(points, centroids);
  }
  return centroids;
}