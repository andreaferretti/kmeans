#include <stdio.h>
#include <stdlib.h>

#include <string.h>
#include <jansson.h>
#include <sys/time.h>

long int getTime() {
    struct timeval tval;
    gettimeofday(&tval, NULL);
    long int ms = ((long int)tval.tv_sec*1000) + ((long int)tval.tv_usec/1000);
    return ms;
}

static json_t *json;

int loadFile() {
  json_error_t error;
  json = json_load_file("../points.json", 0, &error);
  if(!json) {
     printf("Error parsing Json file");
     fflush(stdout);
     return -1;
  }
  return 0;
}

double xValueAt(int i) {
  json_t *value = json_array_get(json, i);
  value = json_array_get(value, 0);
  return json_number_value(value);
}

double yValueAt(int i) {
  json_t *value = json_array_get(json, i);
  value = json_array_get(value, 1);
  return json_number_value(value);
}
