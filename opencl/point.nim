type
  Point* = object
    x*: cfloat
    y*: cfloat
    cluster*: cint

  Centroid* = object
    x*: cfloat
    y*: cfloat

  Accum* = object
    x_sum*: cfloat
    y_sum*: cfloat
    num_points*: cint

