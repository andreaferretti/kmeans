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
}

int times = 100;

int main(void) {

    json_t *json;
    json_error_t error;
    size_t index;
    long int temp = 0;
    json_t *value;

    //PointArray* xs = (PointArray*) malloc(sizeof(PointArray));
    //xs->size = 100000;

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
       // xs->points[index].x = x;
       // xs->points[index].y = y;
    }

    printf("Average Time: %li ms\n", (temp / times));

    return 0;
}
