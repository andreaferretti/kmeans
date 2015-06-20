#include <stdio.h>
#include <stdlib.h>

#include "kmeans.h"
#include "point.h"
#include "configurations.h"

void group_by_cluster(Point* points, Centroid* centroids)
{

    int i, j;

    for (i = 0; i < NUMBER_OF_POINTS; i++) {

        double minor_distance = -1.0;

        for (j = 0; j < NUMBER_OF_CENTROIDS; j++) {
            double my_distance = km_distance(&points[i], &centroids[j]);

            // if my_distance is less than the lower minor_distance 
            // or minor_distance is not yet started
            if (minor_distance > my_distance || minor_distance == -1.0) {
                minor_distance = my_distance;
                points[i].centroid = j;
            }
        }
    }
}

void sum_points_cluster(Point* points, Centroid* centroids)
{

    int i, j;

    for (i =0 ; i < NUMBER_OF_POINTS; i++) {
        for (j = 0; j < NUMBER_OF_CENTROIDS; j++) {
            if (points[i].centroid == j) {
                // #pragma omp atomic
                centroids[j].x_sum = centroids[j].x_sum + points[i].x;

                // #pragma omp atomic
                centroids[j].y_sum = centroids[j].y_sum + points[i].y;

                // #pragma omp atomic
                centroids[j].num_points = centroids[j].num_points + 1;
            }
        }
    }
}

void update_centroids(Centroid* centroids)
{
    int i;

    for (i = 0; i < NUMBER_OF_CENTROIDS; i++) {
        if (centroids[i].num_points > 0) {
            centroids[i].x = centroids[i].x_sum / centroids[i].num_points;
            centroids[i].y = centroids[i].y_sum / centroids[i].num_points;
        }
    }
}

void clear_last_iteration(Centroid* centroids)
{
    int i;

    for (i = 0; i < NUMBER_OF_CENTROIDS; i++) {
        // clear the last iteration sums
        centroids[i].x_sum = 0.0;
        centroids[i].y_sum = 0.0;
        centroids[i].num_points = 0.0;
    }
    
}

/**
* Executes the k-mean algorithm.
*/
void km_execute(Point* points, Centroid* centroids)
{

    int i = 0;

    for (i = 0; i < NUMBER_OF_ITERATIONS; i++) {
        clear_last_iteration(centroids);
        group_by_cluster(points, centroids);
        sum_points_cluster(points, centroids);
        update_centroids(centroids);
    }
}