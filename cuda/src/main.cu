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
#include "configurations.h"

// set to 1 if you want run repository specifications otherwise, 0
int REPOSITORY_SPECIFICATION = 0;

// number of executions of the same algorithim
// its a specification of repository
int TIMES = 100;

// its a repository specification. 
// Its a number of iterations of each k-means execution
int NUMBER_OF_ITERATIONS = 15;

// number of points
int NUMBER_OF_POINTS = 100000;

// number of centroids
int NUMBER_OF_CENTROIDS = 10;

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

    km_execute(points, centroids, NUMBER_OF_POINTS, NUMBER_OF_CENTROIDS);

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

    // load the initial centroids
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

    free(centroids);
    free(points);

    printf("Average Time: %li ms\n", total_time);

    return 0;
}
