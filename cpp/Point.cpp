#include <math.h>
#include "Point.h"

using std::cout;
using std::ostream;

Point::Point() {}

Point::Point(double x_, double y_) {
  x = x_;
  y = y_;
}

Point Point::operator+(const Point& q) const {
  return Point(x + q.x, y + q.y);
}

Point Point::operator-(const Point& q) const {
  return Point(x - q.x, y - q.y);
}

Point Point::operator/(double k) const {
  return Point(x / k, y / k);
}

double Point::norm() const {
  return sqrt(x * x + y * y);
}

bool Point::operator<(const Point& q) const {
  if (x != q.x) {
    return x < q.x;
  } else {
    return y < q.y;
  }
}

double dist(const Point& p, const Point& q) {
  return (p - q).norm();
}

ostream& operator<<(ostream& os, const Point& p) {
  os << "(" << p.x << ", " << p.y << ")";
}