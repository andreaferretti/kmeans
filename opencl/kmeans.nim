import times, json, os, math, strutils, opencl, util, point

proc loadPoints(filename: string): seq[Point] =
  result = newSeq[Point]()
  for p in parseFile(filename).items:
    result.add(Point(x: p[0].fnum, y: p[1].fnum, cluster: -1))

proc main() =
  const
    body = staticRead("kmeans.cl")
    n = 10
    iterations = 100
  var
    points = loadPoints("../points.json")
    centroids = newSeq[Centroid](n)
  let
    platform = getPlatformByName("NVIDIA CUDA")
    devices = platform.getDevices
    context = devices.createContext
    program = context.createProgram(body)
    queue = context.commandQueueFor(devices[0])
    workGroups = devices[0].maxWorkGroups
    workItems = (points.len div workGroups).nextPowerOfTwo

  program.buildOn(devices)

  let
    groupByCluster = program.createKernel("group_by_cluster")
    sumPoints = program.createKernel("sum_points")
    updateCentroids = program.createKernel("update_centroids")
    start = cpuTime()
    gpuPoints = context.buffer(points)
    gpuCentroids = context.buffer(centroids)
    gpuAccum = buffer[Accum](context, centroids.len * workGroups)

  groupByCluster.args(gpuPoints, gpuCentroids, points.len.int32, centroids.len.int32)
  sumPoints.args(gpuPoints, gpuAccum, LocalBuffer[Accum](centroids.len * workItems), points.len.int32, centroids.len.int32)
  updateCentroids.args(gpuAccum, gpuCentroids, workGroups.int32, centroids.len.int32)

  for _ in 1 .. iterations:
    for i in 0 .. < centroids.len:
      centroids[i].x = points[i].x
      centroids[i].y = points[i].y

    queue.write(points, gpuPoints)
    queue.write(centroids, gpuCentroids)

    for _ in 1 .. 15:
      queue.run(groupByCluster, points.len)
      queue.run(sumPoints, workItems * workGroups, workItems)
      queue.run(updateCentroids, centroids.len)

    queue.read(centroids, gpuCentroids)

  let time = (((cpuTime() - start) * 1000) / float(iterations)).round
  echo format("Made $1 iterations with an average of $2 milliseconds",
              iterations, time)

  for a in centroids:
    echo a

  # Clean up
  release(queue)
  release(groupByCluster)
  release(sumPoints)
  release(updateCentroids)
  release(program)
  release(gpuPoints)
  release(gpuCentroids)
  release(context)

when isMainModule:
  main()