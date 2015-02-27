#include <stdlib.h>
#include <stdio.h>
#include"hashmap.h"
#include<string.h>
#include <glib.h>

GHashTable* hash = NULL;

void insert(Point* pkey, Point* pelem)
{
    if (!hash) {
        hash = g_hash_table_new(NULL,NULL);
    }

    PointArray* pa = g_hash_table_lookup (hash, pkey);
    if (pa) {
        pa->points[pa->size].x = pelem->x;
        pa->points[pa->size].y = pelem->y;
        pa->size += 1;
        //g_hash_table_replace(hash, pkey, pa);
    } else {
        PointArray* pa = (PointArray*)malloc(sizeof(PointArray));
        pa->size = 1;
        pa->points[0].x = pelem->x;
        pa->points[0].y = pelem->y;
        g_hash_table_insert(hash, pkey, pa);
    }
}

int i=0;

void iterator(gpointer key, gpointer value, gpointer ret) {
    memcpy(&(((Clusters*)ret)->groups[i]), value , sizeof(PointArray));
    i++;
}

void setCluster(Clusters* ret) {
    i=0;
    g_hash_table_foreach(hash, (GHFunc)iterator, ret);
    g_hash_table_destroy(hash);
    hash = NULL;
}