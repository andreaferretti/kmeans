#include"point.h"

#include <stdlib.h>
#include <stdio.h>

__device__ void km_divide(Point* p, long d) {
    p->x = p->x / ((float) d);
    p->y = p->y / ((float) d);
    return;
}

__device__ void km_add(Point* p1, Point* p2) {
    p1->x = p1->x + p2->x;
    p1->y = p1->y + p2->y;
    return;
}

__device__ void km_sub(Point* p1, Point* p2) {
    p1->x = p1->x - p2->x;
    p1->y = p1->y - p2->y;
    return;
}

__device__ float km_sq(float x) {
    return x * x;
}

__device__ float km_modulus(Point* p) {
    return sqrtf(km_sq(p->x) + km_sq(p->y));
}

__device__ float km_distance(Point* p, Centroid* c)
{
    float dx = p->x - c->x;
    float dy = p->y - c->y;
    return sqrtf(dx*dx + dy*dy);
}
