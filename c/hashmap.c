#include <stdlib.h>
#include <stdio.h>
#include"hashmap.h"
#include"uthash.h"

typedef struct {
    double x;
    double y;
} record_key_t;

typedef struct {
    record_key_t key;
    Point points[100000];
    long pointsSize;
    UT_hash_handle hh;
} record_t;

record_t *records = NULL;

void insert(Point* pkey, Point* pelem)
{
    record_t finder, *p = NULL;
    memset(&finder, 0, sizeof(record_t));
    finder.key.x = pkey->x;
    finder.key.y = pkey->y;
    HASH_FIND(hh, records, &finder.key, sizeof(record_key_t), p);

    if (p) { //FOUND
        p->pointsSize = p->pointsSize+1;
        p->points[p->pointsSize].x = pelem->x;
        p->points[p->pointsSize].y = pelem->y;
        HASH_DEL( records, p);
        HASH_ADD(hh, records, key, sizeof(record_key_t), p);
    } else { //NNEW ELEM
        record_t *r;
        r = (record_t*)malloc( sizeof(record_t) );
        memset(r, 0, sizeof(record_t));
        r->key.x = pkey->x;
        r->key.y = pkey->y;
        r->pointsSize = 1;
        r->points[0].x = pelem->x;
        r->points[0].y = pelem->y;
        HASH_ADD(hh, records, key, sizeof(record_key_t), r);
    }
}

void setCluster(Clusters* ret) {
    long i = 0;
    record_t *p, *tmp = NULL;
    HASH_ITER(hh, records, p, tmp) {
      ret->groups[i].size = p->pointsSize;
      memcpy(ret->groups[i].points, p->points , p->pointsSize*sizeof(Point));
      HASH_DEL(records, p);
      free(p);
      i++;
    }
    return;
}
