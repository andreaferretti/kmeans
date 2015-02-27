#ifndef KMEANS_H_INCLUDED
#define KMEANS_H_INCLUDED

#include"point.h"

typedef struct {
    long size;
    Point points[100000];
} PointArray;

typedef struct {
    long size;
    PointArray groups[10];
} Clusters;

void run(PointArray* xs, Clusters* clusters);

#endif // KMEANS_H_INCLUDED
