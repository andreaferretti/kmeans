#include<math.h>
#include"point.h"

#include <stdlib.h>
#include <stdio.h>

__device__ void divide(Point* p, long d) {
    p->x = p->x / ((double) d);
    p->y = p->y / ((double) d);
    return;
}

__device__ void add(Point* p1, Point* p2) {
    p1->x = p1->x + p2->x;
    p1->y = p1->y + p2->y;
    return;
}

__device__ void sub(Point* p1, Point* p2) {
    p1->x = p1->x - p2->x;
    p1->y = p1->y - p2->y;
    return;
}

__device__ double sq(double x) {
    return x * x;
}

__device__ double modulus(Point* p) {
    return sqrt(sq(p->x) + sq(p->y));
}

__device__ double distance(Point* p1, Point* p2) {
    double dx = p2->x - p1->x;
    double dy = p2->y - p1->y;
    return sqrt(dx*dx + dy*dy);
}
