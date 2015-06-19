#ifndef HASHMAP_H_INCLUDED
#define HASHMAP_H_INCLUDED

#include"point.h"
#include"kmeans.h"

void insert(Point* key, Point* elem);

void printHashMap();

void setCluster(Clusters* ret);

#endif // HASHMAP_H_INCLUDED
