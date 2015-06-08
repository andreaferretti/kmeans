#include <sys/types.h>
#include <math.h>
#include <errno.h>

#include <stdio.h>
#include <stdlib.h>

#include <string.h>
#include <jansson.h>
#include <sys/time.h>

extern "C" {
#include"point.h"
#include"hashmap.h"
}

int times = 100;

long int getTime(PointArray* xs, Clusters* clusters) {
    int i = 0;
    struct timeval tval_before, tval_after, tval_result;
    gettimeofday(&tval_before, NULL);

    for (i = 0; i < times; i++) {
        run(xs, clusters);
    }

    gettimeofday(&tval_after, NULL);
    timersub(&tval_after, &tval_before, &tval_result);
    long int ms = ((long int) tval_result.tv_sec * 1000)
            + ((long int) tval_result.tv_usec / 1000);
    return ms;
}

int main(void) {

    json_t *json;
    json_error_t error;
    size_t index;
    long int temp = 0;
    json_t *value;

    PointArray* xs = (PointArray*) malloc(sizeof(PointArray));
    xs->size = 100000;

    json = json_load_file("../points.json", 0, &error);

    if (!json) {
        printf("Error parsing Json file");
        fflush(stdout);
        return -1;
    }

    json_array_foreach(json, index, value)
    {
        double x = json_number_value(json_array_get(value, 0));
        double y = json_number_value(json_array_get(value, 1));
        xs->points[index].x = x;
        xs->points[index].y = y;
    }

    Clusters* clusters = (Clusters*) malloc(sizeof(Clusters));

    temp += getTime(xs, clusters);

    printf("Average Time: %li ms\n", (temp / times));

    return 0;
}
