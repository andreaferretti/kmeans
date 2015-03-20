#include <cstdlib>
#include <cstdio>
#include <jansson.h>

double c_xs[100000];
double c_ys[100000];
int c_xs_counter = 0;
int c_ys_counter = 0;

void parse() { 
	int i=0;
	json_t *json;
	json_error_t error;
	size_t index;
	long int temp = 0;
	json_t *value;
	json = json_load_file("../points.json", 0, &error);
	json_array_foreach(json, index, value) {
		double x = json_number_value(json_array_get(value,0));
		double y = json_number_value(json_array_get(value,1));
		c_xs[index] = x;
		c_ys[index] = y;
	};
}

double nextX() {
	return c_xs[c_xs_counter++];
}

double nextY() {
	return c_ys[c_ys_counter++];
}