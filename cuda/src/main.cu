#include <sys/types.h>
#include <math.h>
#include <errno.h>

#include <stdio.h>
#include <stdlib.h>

#include <string.h>
#include <jansson.h>
#include <sys/time.h>

#include "point.h"
#include "kmeans.h"
#include "config.h"

int NUMBER_OF_POINTS = 100000;
int NUMBER_OF_CENTROIDS = 10;

void print_me(Centroid* centroids) {

    if (!DEBUG_LOGS) {
        return;
    }

    for (int i = 0; i < NUMBER_OF_CENTROIDS; i++) {
        printf("[x=%lf, y=%lf, x_sum=%lf, y_sum=%lf, num_points=%i]\n", 
               centroids[i].x, centroids[i].y, centroids[i].x_sum,
               centroids[i].y_sum, centroids[i].num_points);
    }

    printf("--------------------------------------------------\n");
}

long int run_kmeans_repo_specifications(Point* points, Centroid* centroids) {
    struct timeval time_before, time_after, time_result;
    gettimeofday(&time_before, NULL);

    // load the initial centroids
    for (int ci = 0; ci < NUMBER_OF_CENTROIDS; ci++) {
        centroids[ci].x = points[ci].x;
        centroids[ci].y = points[ci].y;
    }

    print_me(centroids);

    for (int i = 0; i < TIMES; i++) {

        km_execute(points, centroids, NUMBER_OF_POINTS, NUMBER_OF_CENTROIDS);

        if (i + 1 == TIMES) {
            print_me(centroids);
        } else {
            // load the centroids to next iteration
            for (int ci = 0; ci < NUMBER_OF_CENTROIDS; ci++) {
                centroids[ci].x = points[ci].x;
                centroids[ci].y = points[ci].y;
            }
        }
    }

    gettimeofday(&time_after, NULL);
    timersub(&time_after, &time_before, &time_result);
    long int ms = ((long int)time_result.tv_sec*1000) + ((long int)time_result.tv_usec/1000);

    return ms / TIMES;
}

long int run_kmeans_rocks(Point* points, Centroid* centroids) {
    // load the initial centroids
    for (int i = 0; i < NUMBER_OF_CENTROIDS; i++) {
        centroids[i].x = points[i].x;
        centroids[i].y = points[i].y;
    }

    print_me(centroids);

    struct timeval time_before, time_after, time_result;
    gettimeofday(&time_before, NULL);

    km_execute(points, centroids, NUMBER_OF_POINTS, NUMBER_OF_CENTROIDS);

    gettimeofday(&time_after, NULL);
    timersub(&time_after, &time_before, &time_result);
    long int ms = ((long int)time_result.tv_sec*1000) + ((long int)time_result.tv_usec/1000);

    print_me(centroids);

    return ms; 
}

int main(int argc, char *argv[])
{
    json_t *json;
    json_error_t error;
    size_t index;
    long int total_time = 0;
    json_t *value;

    if (argc > 1 && argc < 4) {
        printf("Usage: ./kmeans.out [input_file.json number_of_points number_of_centroids]\n");
        return 0;
    }

    if (argc == 4) {
        json = json_load_file(argv[1], 0, &error);
        NUMBER_OF_POINTS = atoi(argv[2]);
        NUMBER_OF_CENTROIDS = atoi(argv[3]);
    }
    else {
        json = json_load_file("../points.json", 0, &error);
    }

    cudaSetDevice(0);

    // 100.000 points it's the repository default.
    Point* points = (Point*) malloc(NUMBER_OF_POINTS * sizeof(Point));
    Centroid* centroids = (Centroid*) malloc(NUMBER_OF_CENTROIDS * sizeof(Centroid));

    json = json_load_file("../points.json", 0, &error);

    // validates json
    if (!json) {
        printf("Error parsing Json file");
        fflush(stdout);
        return -1;
    }

    // load points from json
    json_array_foreach(json, index, value)
    {
        float x = json_number_value(json_array_get(value, 0));
        float y = json_number_value(json_array_get(value, 1));
        points[index].x = x;
        points[index].y = y;
    }

    // call K-means
    if (REPOSITORY_SPECIFICATION == 1) {
        total_time = run_kmeans_repo_specifications(points, centroids);
    } else {
        total_time = run_kmeans_rocks(points, centroids);
    }

    free(centroids);
    free(points);

    printf("Average Time: %li ms\n", total_time);

    cudaDeviceReset();

    return 0;
}
