#ifndef POINT_H_INCLUDED
#define POINT_H_INCLUDED

typedef struct {
    double x;
    double y;
    int centroid;
} Point;

typedef struct {
    double x;
    double y;
    double x_sum;
    double y_sum;
    int num_points;
} Centroid;


void divide(Point* p, long d);

void add(Point* p1, Point* p2);

void sub(Point* p1, Point* p2);

double sq(double x);

double modulus(Point* p);

double km_distance(Point* p, Centroid* c);

#endif // POINT_H_INCLUDED
