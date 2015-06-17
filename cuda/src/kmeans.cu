#include <stdio.h>
#include <stdlib.h>

#include "kmeans.h"
#include "point.h"
#include "configurations.h"

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

/**
* Copy points from host memory to device memory
*/
void copy_points_to_kernel(Point* h_points, Point* d_points, int array_size) {
    cudaMalloc((void **) &d_points, sizeof(Point) * array_size);
    cudaMemcpy(d_points, h_points, sizeof(Point) * array_size, cudaMemcpyHostToDevice);
}

/**
* Copy centroids from host memory to device memory.
*/
void copy_centroids_to_kernel(Centroid* h_centroids, Centroid* d_centroids, int array_size) {
    cudaMalloc((void **) &d_centroids, sizeof(Centroid) * array_size);
    cudaMemcpy(d_centroids, h_centroids, sizeof(Centroid) * array_size, cudaMemcpyHostToDevice);
}

/**
* Executes the k-mean algorithm.
*/
void km_execute(Point* h_points, Centroid* h_centroids, int num_points,
        int num_centroids)
{
    int continue_iterations = 1;
    int iterations;
    Point* d_points;
    Centroid* d_centroids;

    copy_points_to_kernel(h_points, d_points, num_points);
    copy_centroids_to_kernel(h_centroids, d_centroids, num_centroids);

    while (continue_iterations) {
        iterations++;

        // TODO: call kernel here! 

        if (REPOSITORY_SPECIFICATION == 1) {
            // in repository specifications, 
            // we just want know if number of 
            // iterations is equals NUMBER_OF_ITERATIONS
            if (iterations == NUMBER_OF_ITERATIONS) {
                continue_iterations = 0;
            }
        } else {
            // TODO: TEST centroids of last iteration equals actual centroids
            continue_iterations = 0; // set 1 here, just for pre implementation
        }
    }

    cudaFree(d_points);
    cudaFree(d_centroids);
}
