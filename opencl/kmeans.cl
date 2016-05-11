#include "point.h"


float dist(__global Point* p, __global Centroid* c)
{
    float dx = p->x - c->x;
    float dy = p->y - c->y;
    return dx*dx + dy*dy;
}

__kernel void group_by_cluster(__global Point* points, __global Centroid* centroids, int num_points, int num_centroids) {
  int idx = get_global_id(0);
  int i = 0;
  float min_distance = -1.0;

  if (idx < num_points) {
    for (i = 0; i < num_centroids; i++) {
      float d = dist(points + idx,  centroids + i);

      if (min_distance > d || min_distance == -1.0) {
        min_distance = d;
        points[idx].cluster = i;
      }
    }
  }
}

__kernel void sum_points(__global Point* points, __global Accum* accum, __local Accum* scratch, int num_points, int num_centroids) {
  int lid = get_local_id(0);
  int gid = get_global_id(0);
  int wid = get_group_id(0);
  int pos = lid * num_centroids;
  int s;
  int j;

  for (s = pos; s < pos + num_centroids; s++) {
    scratch[s].x_sum = 0.0;
    scratch[s].y_sum = 0.0;
    scratch[s].num_points = 0;
  }

  if (gid < num_points) {
    int cluster = points[gid].cluster;
    scratch[pos + cluster].x_sum = points[gid].x;
    scratch[pos + cluster].y_sum = points[gid].y;
    scratch[pos + cluster].num_points = 1;
  }
  barrier(CLK_LOCAL_MEM_FENCE);

  for(s = get_local_size(0) / 2; s > 0; s = s / 2) {
    if (lid < s) {
      for (j = 0; j < num_centroids; j++) {
        int dst = pos + j;
        int src = pos + j + s * num_centroids;
        scratch[dst].x_sum += scratch[src].x_sum;
        scratch[dst].y_sum += scratch[src].y_sum;
        scratch[dst].num_points += scratch[src].num_points;
      }
    }
    barrier(CLK_LOCAL_MEM_FENCE);
  }

  if (lid == 0) {
    for (j = 0; j < num_centroids; j++) {
      int h = wid * num_centroids + j;
      accum[h].x_sum = scratch[pos + j].x_sum;
      accum[h].y_sum = scratch[pos + j].y_sum;
      accum[h].num_points = scratch[pos + j].num_points;
    }
  }
}

__kernel void update_centroids(__global Accum* accum, __global Centroid* centroids, int work_groups, int num_centroids) {
  int gid = get_global_id(0);
  float x_sum = 0.0;
  float y_sum = 0.0;
  int num_points = 0;
  int j;

  if (gid < num_centroids) {
    for (j = 0; j < work_groups; j++) {
      int h = j * num_centroids + gid;
      x_sum += accum[h].x_sum;
      y_sum += accum[h].y_sum;
      num_points += accum[h].num_points;
    }
    if (num_points > 0) {
      centroids[gid].x = x_sum / num_points;
      centroids[gid].y = y_sum / num_points;
    }
  }
}