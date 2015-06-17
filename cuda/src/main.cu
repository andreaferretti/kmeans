#include <sys/types.h>
#include <math.h>
#include <errno.h>

#include <stdio.h>
#include <stdlib.h>

#include <string.h>
#include <jansson.h>
#include <sys/time.h>

#include "point.h"
#include "configurations.h"

long int run_kmeans_repo_specifications(Point* points, Centroid* centroids) {
    struct timeval time_before, time_after, time_result;
    gettimeofday(&time_before, NULL);

    for (int i = 0; i < TIMES; i++) {
        // TODO
    }

    gettimeofday(&time_after, NULL);
    timersub(&time_after, &time_before, &time_result);
    long int ms = ((long int)time_result.tv_sec*1000) + ((long int)time_result.tv_usec/1000);

    return ms / TIMES;    
}

long int run_kmeans_rocks(Point* points, Centroid* centroids) {
    struct timeval time_before, time_after, time_result;
    gettimeofday(&time_before, NULL);

    for (int i = 0; i < TIMES; i++) {
        // TODO
    }

    gettimeofday(&time_after, NULL);
    timersub(&time_after, &time_before, &time_result);
    long int ms = ((long int)time_result.tv_sec*1000) + ((long int)time_result.tv_usec/1000);
    return ms; 
}

int main(void) {

    json_t *json;
    json_error_t error;
    size_t index;
    long int total_time = 0;
    json_t *value;

    // 100.000 points it's the repository default.
    Point* points = (Point*) malloc(NUMBER_OF_POINTS * sizeof(Point));
    Centroid* centroids = (Centroid*) malloc(NUMBER_OF_CENTROIDS * sizeof(Centroid));

    json = json_load_file("../points.json", 0, &error);

    if (!json) {
        printf("Error parsing Json file");
        fflush(stdout);
        return -1;
    }

    json_array_foreach(json, index, value)
    {
        float x = json_number_value(json_array_get(value, 0));
        float y = json_number_value(json_array_get(value, 1));
        points[index].x = x;
        points[index].y = y;
    }

    for (int i = 0; i < NUMBER_OF_CENTROIDS; i++) {
        centroids[i].x = points[i].x;
        centroids[i].y = points[i].y;
        // TODO: what we considering a centroid ID?
    }

    // call K-means
    if (REPOSITORY_SPECIFICATION == 1) {
        total_time = run_kmeans_repo_specifications(points, centroids);
    } else {
        total_time = run_kmeans_rocks(points, centroids);
    }

    printf("Average Time: %li ms\n", total_time);

    return 0;
}
