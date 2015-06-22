#include <stdio.h>
#include <stdlib.h>

#include "point.h"

#include <string.h>
#include <jansson.h>
#include <sys/time.h>
#include <omp.h>
#include "kmeans.h"

#include "config.h"

int NUMBER_OF_POINTS = 100000;
int NUMBER_OF_CENTROIDS = 10;
int NUM_THREAD = 4;

void print_me(Centroid* centroids)
{

    if (DEBUG_LOGS == 0) {
        return;
    }

    int i;

    for (i = 0; i < NUMBER_OF_CENTROIDS; i++) {
        printf("[x=%lf, y=%lf, x_sum=%lf, y_sum=%lf, num_points=%i]\n",
                centroids[i].x, centroids[i].y, centroids[i].x_sum,
                centroids[i].y_sum, centroids[i].num_points);
    }

    printf("--------------------------------------------------\n");
}

long int run_kmeans(Point* points, Centroid* centroids)
{
    struct timeval time_before, time_after, time_result;
    gettimeofday(&time_before, NULL);

    int ci, i;

    // # pragma omp parallel for num_threads(NUMBER_OF_THREADS) default(none) private(ci) firstprivate(points, centroids, NUMBER_OF_CENTROIDS)
    // load the initial centroids
    for (ci = 0; ci < NUMBER_OF_CENTROIDS; ci++) {
        centroids[ci].x = points[ci].x;
        centroids[ci].y = points[ci].y;
        centroids[ci].x_sum = 0;
        centroids[ci].y_sum = 0;
        centroids[ci].num_points = 0;
    }

    print_me(centroids);

    for (i = 0; i < TIMES; i++) {

        km_execute(points, centroids);

        if (i + 1 == TIMES) {
            print_me(centroids);
        } else {
            // load the centroids to next iteration
            for (ci = 0; ci < NUMBER_OF_CENTROIDS; ci++) {
                centroids[ci].x = points[ci].x;
                centroids[ci].y = points[ci].y;
            }
        }
    }

    gettimeofday(&time_after, NULL);
    timersub(&time_after, &time_before, &time_result);
    long int ms = ((long int) time_result.tv_sec * 1000)
            + ((long int) time_result.tv_usec / 1000);

    return ms / TIMES;
}

int main(int argc, char *argv[])
{
    json_t *json;
    json_error_t error;
    size_t index;
    long int total_time = 0;
    json_t *value;

    if (argc > 1 && argc < 5) {
        printf("Usage: ./kmeans.out [input_file.json number_of_points number_of_centroids number_of_threads]\n");
        printf("or... ./kmeans.out [number_of_threads] \n");
        return 0;
    }

    if (argc == 2) {
        NUM_THREAD = atoi(argv[1]);
        json = json_load_file("../points.json", 0, &error);
    }
    else if (argc == 5) {
        json = json_load_file(argv[1], 0, &error);
        NUMBER_OF_POINTS = atoi(argv[2]);
        NUMBER_OF_CENTROIDS = atoi(argv[3]);
        NUM_THREAD = atoi(argv[4]);
    }
    else {
        json = json_load_file("../points.json", 0, &error);
    }

    // printf("NUM_THREAD: %i | NUM_OF_POINTS: %i | NUM_OF_CENTROIDS: %i | FILENAME %s\n", NUM_THREAD, NUMBER_OF_POINTS, NUMBER_OF_CENTROIDS, argv[1]);

    Point* points = (Point*) malloc(NUMBER_OF_POINTS * sizeof(Point));
    Centroid* centroids = (Centroid*) malloc(
            NUMBER_OF_CENTROIDS * sizeof(Centroid));

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
        points[index].centroid = 0;
    }

    // call K-means  
    total_time = run_kmeans(points, centroids);

    free(centroids);
    free(points);

    printf("Average Time: %li ms\n", total_time);

    return 0;
}
