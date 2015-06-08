#include <stdio.h>
#include <stdlib.h>

extern "C" {
#include "kmeans.h"
#include "point.h"
#include "hashmap.h"
}

int n = 10;
int iters = 15;

const long points_number = 100000;

double dist(Point* p1, Point* p2) {
    Point p = { p1->x, p1->y };
    sub(&p, p2);
    double result = modulus(&p);
    return result;
}

void average(PointArray* xs, Point* ret) {
    long i;
    ret->x = xs->points[0].x;
    ret->y = xs->points[0].y;

    for (i = 1; i < xs->size; i++) {
        add(ret, &(xs->points[i]));
    }

    divide(ret, xs->size);
    return;
}

long closest(Point* p, PointArray* choices) {
    long i;
    double minVal = dist(p, &(choices->points[0]));
    long min = 0;

    for (i = 1; i < choices->size; i++) {
        double actualDist = dist(p, &(choices->points[i]));
        if (actualDist < minVal) {
            min = i;
            minVal = actualDist;
        }
    }

    return min;
}

void calcClusters(PointArray* xs, Clusters* clusters, PointArray* centroids) {
    long i = 0;
    long theClosest = 0;
    clusters->size = 10;

    for (i = 0; i < 10; i++) {
        clusters->groups[i].size = 0;
    }

    for (i = 0; i < xs->size; i++) {
        //printf("punto %d",i);
        theClosest = closest(&(xs->points[i]), centroids);
        insert(&(xs->points[theClosest]), &(xs->points[i]));
    }

    setCluster(clusters);

    return;
}

void run(PointArray* xs, Clusters* clusters) {
    long i, k;
    Point* temp = (Point*) malloc(sizeof(Point));
    PointArray* centroids = (PointArray*) malloc(sizeof(PointArray));
    centroids->size = n;

    for (i = 0; i < n; i++) {
        centroids->points[i].x = xs->points[i].x;
        centroids->points[i].y = xs->points[i].y;
    }

    clusters->size = iters;

    for (k = 0; k < iters; k++) {
        calcClusters(xs, clusters, centroids);

        for (i = 0; i < n; i++) {
            average(&(clusters->groups[i]), temp);
            centroids->points[i].x = temp->x;
            centroids->points[i].y = temp->y;
        }
    }

    free(temp);
    free(centroids);

    return;
}
