#ifndef POINT_H_INCLUDED
#define POINT_H_INCLUDED

typedef struct {
    double x;
    double y;
    int cluster;
} Point;

__device__ void divide(Point* p, long d);

__device__ void add(Point* p1, Point* p2);

__device__ void sub(Point* p1, Point* p2);

__device__ double sq(double x);

__device__ double modulus(Point* p);

__device__ double distance(Point* p1, Point* p2);

#endif // POINT_H_INCLUDED
