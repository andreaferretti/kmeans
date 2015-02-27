#include <stdio.h>
#include <stdlib.h>

#include"point.h"
#include"hashmap.h"

#include <string.h>
#include <jansson.h>
#include <sys/time.h>

long int getTime(PointArray* xs, Clusters* clusters) {
    struct timeval tval_before, tval_after, tval_result;
    gettimeofday(&tval_before, NULL);
    run(xs, clusters);
    gettimeofday(&tval_after, NULL);
    timersub(&tval_after, &tval_before, &tval_result);
    long int ms = ((long int)tval_result.tv_sec) + ((long int)tval_result.tv_usec/1000);
    return ms;
}

int main()
{
   int i=0;
   json_t *json;
   json_error_t error;
   size_t index;
   long int temp = 0;
   json_t *value;

   PointArray* xs = (PointArray*)malloc(sizeof(PointArray));
   xs->size = 100000;

   json = json_load_file("../points.json", 0, &error);
   if(!json) {
        printf("Error parsing Json file");
        fflush(stdout);
        return -1;
    }

    json_array_foreach(json, index, value) {
        double x = json_number_value(json_array_get(value,0));
        double y = json_number_value(json_array_get(value,1));
        xs->points[index].x = x;
        xs->points[index].y = y;
    }

   Clusters* clusters = (Clusters*)malloc(sizeof(Clusters));

   for (i=0;i<100;i++) {
    temp += getTime(xs, clusters);
   }

   printf("Average Time: %li ms\n", (temp/100));

   return 0;
}
