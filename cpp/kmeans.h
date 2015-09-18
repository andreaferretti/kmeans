#ifndef GUARD_Kmeans
#define GUARD_Kmeans

#include <vector>
#include "Point.h"

std::vector<Point> kmeans(const std::vector<Point>& points, int n, int iterations);

#endif