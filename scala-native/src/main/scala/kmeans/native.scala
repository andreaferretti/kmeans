package kmeans

import scalanative.native._

@link("jansson")
@extern object Jansson {
  // in C enums become ints at runtime
  type json_type = Int

  @struct
  class json_t(
    val typ: json_type = 0,
    val refcount: CInt = 0
  )

  @struct
  class json_error_t(
    val line: CInt = 0,
    val column: CInt = 0,
    val position: CInt = 0
    // can't express source field due to #35
    // can't express test field due to #35
  )

  def json_load_file(path: Ptr[Byte], flags: CSize,
      error: Ptr[json_error_t]): Ptr[json_t] = extern

  def json_array_get(array: Ptr[json_t], index: CInt): Ptr[json_t] = extern

  def json_number_value(json: Ptr[json_t]): CDouble = extern
}

@name("sys/time")
@extern object SysTime {

  @struct
  class timeval(
    val tv_sec: CLong = 0L,
    val tv_usec: CLong = 0L
  )

  def gettimeofday(tp: Ptr[timeval], tzp: Ptr[_]): CInt = extern
}
