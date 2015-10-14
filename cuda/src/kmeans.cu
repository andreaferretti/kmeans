#include <stdio.h>
#include <stdlib.h>

#include "kmeans.h"
#include "point.h"
#include "config.h"

/**
    Groups the points in a centroid.
*/
__global__ void km_group_by_cluster(Point* points, Centroid* centroids,
        int num_centroids, int num_points)
{
    int idx = blockIdx.x * blockDim.x + threadIdx.x;
    
    int i = 0;

    float minor_distance = -1.0;

	if (idx < num_points) {
	    for (i = 0; i < num_centroids; i++) {
	        float my_distance = km_distance(&points[idx], &centroids[i]);
	
	        // if my_distance is less than the lower minor_distance 
	        // or minor_distance is not yet started
	        if (minor_distance > my_distance || minor_distance == -1.0) {
	            minor_distance = my_distance;
	            points[idx].cluster = i;
	        }
	    }
	}
}

/**
    Sum the points of each centroid
*/
__global__ void km_sum_points_cluster(Point* points, Centroid* centroids,
        int num_centroids, int num_points)
{
    extern __shared__ Centroid s_centroids[];
    
    int tdx = threadIdx.x;
    int idx = blockIdx.x * blockDim.x + threadIdx.x;

    // init global memory
    if (idx < num_centroids) {
        centroids[idx].x_sum = 0.0;
        centroids[idx].y_sum = 0.0;
        centroids[idx].num_points = 0.0;
    }

    // init shared memory
    if (tdx < num_centroids) {
        s_centroids[tdx].x_sum = 0.0;
        s_centroids[tdx].y_sum = 0.0;
        s_centroids[tdx].num_points = 0.0;
    }

    __syncthreads();
    
    // use shared memory for intermediate sums
    if (idx < num_points) {
        int i = points[idx].cluster;
        atomicAdd(&s_centroids[i].x_sum, points[idx].x);
        atomicAdd(&s_centroids[i].y_sum, points[idx].y);
        atomicAdd(&s_centroids[i].num_points, 1);
	}

    __syncthreads();
    
    // then sum partial results in global memory, thus we reduce accesses to global memory
    if (tdx < num_centroids) {
        atomicAdd(&centroids[tdx].x_sum, s_centroids[tdx].x_sum);
        atomicAdd(&centroids[tdx].y_sum, s_centroids[tdx].y_sum);
        atomicAdd(&centroids[tdx].num_points, s_centroids[tdx].num_points);
    }
}

/**
    Update the centroids with current clustering.
    Gets the x and y sum and divides by number of point for each centroid.\
*/
__global__ void km_update_centroids(Centroid* centroids, int num_centroids)
{
    int idx = blockIdx.x * blockDim.x + threadIdx.x;

	if (idx < num_centroids) {
	    if (centroids[idx].num_points > 0) {
	        centroids[idx].x = centroids[idx].x_sum / centroids[idx].num_points;
	        centroids[idx].y = centroids[idx].y_sum / centroids[idx].num_points;
	    }
	}
}

/**
    Compare the clusters of each point.
    @param p1 - points of current iteration
    @param p2 - points of last iteration
*/
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
    Copy a point array.
    Utilized to copy the status of points on the last iteration to compare them.
*/
__global__ void km_points_copy(Point* p_dest, Point* p_src, int num_points)
{
    int idx = blockIdx.x * blockDim.x + threadIdx.x;

    if (idx < num_points) {
        p_dest[idx] = p_src[idx];
    }
}

/**
* Executes the k-mean algorithm.
* To measure your global methods, use that:
*
*    cudaEvent_t start, stop;
*    float time;
*    cudaEventCreate(&start);
*    cudaEventCreate(&stop);
*    cudaEventRecord(start, 0);
*
* //  put your__global__ method here!
*
*    cudaEventRecord(stop, 0);
*    cudaEventSynchronize(stop);
*    cudaEventElapsedTime(&time, start, stop);
*    printf("%lf\n", times)
*/
void km_execute(Point* h_points, Centroid* h_centroids, int num_points,
        int num_centroids)
{
    int iterations = 0;
    Point* d_points;
    Point* d_points_old;
    Centroid* d_centroids;
    int h_res = 1;
    int *d_res;

    cudaMalloc((void**) &d_res, sizeof(int));
    cudaMalloc((void**) &d_points_old, sizeof(Point) * num_points);
    cudaMalloc((void **) &d_points, sizeof(Point) * num_points);
    cudaMalloc((void **) &d_centroids, sizeof(Centroid) * num_centroids);

    cudaMemcpy(d_points, h_points, sizeof(Point) * num_points, cudaMemcpyHostToDevice);
    cudaMemcpy(d_centroids, h_centroids, sizeof(Centroid) * num_centroids, cudaMemcpyHostToDevice);   

    while (true) {

        km_group_by_cluster<<<ceil(num_points/100), 100>>>(d_points, d_centroids,
                num_centroids, num_points);
        cudaDeviceSynchronize();
        
        km_sum_points_cluster<<<ceil(num_points/100), 100, num_centroids*sizeof(Centroid)>>>(d_points, d_centroids,
                num_centroids, num_points);
        cudaDeviceSynchronize();

        km_update_centroids<<<ceil(num_centroids/10), 10>>>(d_centroids, num_centroids);
        cudaDeviceSynchronize();

        if (REPOSITORY_SPECIFICATION == 1) {
            // in repository specifications, 
            // we just want know if number of 
            // iterations is equals NUMBER_OF_ITERATIONS - 1 (iterations starts in 0)
            if (iterations == (NUMBER_OF_ITERATIONS - 1)) {
                break;
            }
        } else {
            // TODO: WARNING:
            // THIS IMPLEMENTATION IS NOT WORKING YET!
            if (iterations > 0) {
                h_res = 1;
                cudaMemcpy(d_res, &h_res , sizeof(int), cudaMemcpyHostToDevice);
                km_points_compare<<<ceil(num_points/10), 10>>>(d_points, d_points_old,
                        num_points, d_res);
                cudaDeviceSynchronize();

                cudaMemcpy(&h_res, d_res, sizeof(int), cudaMemcpyDeviceToHost);

                // if h_rest == 1 the two vector of points are equal and the kmeans iterations
                // has completed all work
                if (h_res == 1) {
                    break;
                }
            }

            km_points_copy<<<ceil(num_points/100), 100>>>(d_points_old, d_points,
                num_points);
            cudaDeviceSynchronize();
        }
        
        iterations++;
    }

    cudaMemcpy(h_centroids, d_centroids , sizeof(Centroid) * num_centroids, cudaMemcpyDeviceToHost);

    cudaFree(d_points);
    cudaFree(d_centroids);
    cudaFree(d_points_old);
    cudaFree(d_res);
}
