typedef struct {
  float x;
  float y;
  int cluster;
} Point;

typedef struct {
  float x;
  float y;
} Centroid;

typedef struct {
  float x_sum;
  float y_sum;
  int num_points;
} Accum;