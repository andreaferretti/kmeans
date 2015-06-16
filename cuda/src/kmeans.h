#ifndef KMEANS_H_INCLUDED
#define KMEANS_H_INCLUDED

#include "point.h"

__global__ void km_group_by_cluster(Point* points, Centroid* centroids,
        int num_centroids);

__global__ void km_sum_points_cluter(Point* points, Centroid* centroids,
        int num_centroids);

__global__ void km_update_centroids(Centroid* centroids);

__global__ void km_points_compare(Point* p1, Point* p2, int num_points,
        int *result);

void km_execute(Point* h_points, Centroid* h_centroids, int num_points,
        int num_centroids);

#endif // KMEANS_H_INCLUDED
