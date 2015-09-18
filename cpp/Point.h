#ifndef GUARD_Point
#define GUARD_Point

#include <iostream>

class Point {
  public:
    double x, y;
    Point();
    Point(double x_, double y_);
    Point operator+(const Point& q) const;
    Point operator-(const Point& q) const;
    Point operator/(double k) const;
    double norm() const;
    bool operator==(const Point& q) const;
    bool operator<(const Point& q) const;

    friend std::ostream& operator<<(std::ostream& os, const Point& p);
};

double dist(const Point& p, const Point& q);

namespace std {
  template <>
  struct hash<Point> {
    size_t operator()(const Point& p) const {
      return hash<double>()(p.x) ^ (hash<double>()(p.y) << 1);
    }
  };
}

#endif