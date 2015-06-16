#ifndef POINT_H_INCLUDED
#define POINT_H_INCLUDED

typedef struct {
    float x;
    float y;
    int cluster;
} Point;

typedef struct {
    float x;
    float y;
    float x_sum;
    float y_sum;
    int num_points;
} Centroid;

__device__ void km_divide(Point* p, long d);

__device__ void km_add(Point* p1, Point* p2);

__device__ void km_sub(Point* p1, Point* p2);

__device__ float km_sq(float x);

__device__ float km_modulus(Point* p);

__device__ float km_distance(Point* p, Centroid* c);

#endif // POINT_H_INCLUDED
