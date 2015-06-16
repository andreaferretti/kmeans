#include <stdio.h>
#include <stdlib.h>

#include "kmeans.h"
#include "point.h"

__global__ void km_group_by_cluster(Point* points, Centroid* centroids,
        int num_centroids)
{
    int idx = blockIdx.x * blockDim.x + threadIdx.x;
    int i = 0;

    float minor_distance = -1;

    for (i = 0; i < num_centroids; i++) {
        float diff = km_distance(&points[idx], &centroids[i]);

        // se a diferenca for menor que a menor distancia existente,
        // ou minor distance nao tiver sido inicializada
        if (minor_distance > diff || minor_distance == -1.0) {
            minor_distance = diff;
            points[idx].cluster = i;
        }
    }
}

__global__ void km_sum_points_cluter(Point* points, Centroid* centroids,
        int num_centroids)
{
    int idx = blockIdx.x * blockDim.x + threadIdx.x;

    for (int i = 0; i < num_centroids; i++) {
        if (points[idx].cluster == i) {
            atomicAdd(&centroids[i].x_sum, points[idx].x);
            atomicAdd(&centroids[i].y_sum, points[idx].y);
            atomicAdd(&centroids[i].num_points, 1);
        }
    }
}

__global__ void km_update_centroids(Centroid* centroids)
{
    int idx = blockIdx.x * blockDim.x + threadIdx.x;

    if (centroids[idx].num_points > 0) {
        centroids[idx].x = centroids[idx].x_sum / centroids[idx].num_points;
        centroids[idx].y = centroids[idx].y_sum / centroids[idx].num_points;
    }
}

__global__ void km_points_compare(Point* p1, Point* p2, int num_points,
        int *result)
{
    int idx = blockIdx.x * blockDim.x + threadIdx.x;

    if (idx < num_points) {
        // if any points has its cluster different, changes the result variable
        if (p1[idx].cluster != p2[idx].cluster) {
            *result = 0;
        }
    }
}

void km_execute(Point* h_points, Centroid* h_centroids, int num_points,
        int num_centroids)
{

}
