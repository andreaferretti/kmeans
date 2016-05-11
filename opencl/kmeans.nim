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
    pointSize = points.len * sizeof(Point)
    centroidSize = centroids.len * sizeof(Centroid)
    workGroups = devices[0].maxWorkGroups
    workItems = (points.len div workGroups).nextPowerOfTwo
    parallelism = workItems * workGroups
    accumSize = centroids.len * workGroups * sizeof(Accum)
  program.buildOn(devices)

  let
    groupByCluster = program.createKernel("group_by_cluster")
    sumPoints = program.createKernel("sum_points")
    updateCentroids = program.createKernel("update_centroids")

  let start = cpuTime()

  var status: TClResult
  var gpuPoints = createBuffer(context, MEM_READ_WRITE, pointSize, nil, addr status)
  check status
  var gpuCentroids = createBuffer(context, MEM_READ_WRITE, centroidSize, nil, addr status)
  check status
  var gpuAccum = createBuffer(context, MEM_READ_WRITE, accumSize, nil, addr status)
  check status

  groupByCluster.bindArgs([gpuPoints, gpuCentroids], [points.len.int32, centroids.len.int32])
  var
    numPoints = points.len.int32
    numCentroids = centroids.len.int32
  check setKernelArg(sumPoints, 0, sizeof(Pmem), addr gpuPoints)
  check setKernelArg(sumPoints, 1, sizeof(Pmem), addr gpuAccum)
  check setKernelArg(sumPoints, 2, workItems * centroids.len * sizeof(Accum), nil)
  check setKernelArg(sumPoints, 3, sizeof(int32), addr numPoints)
  check setKernelArg(sumPoints, 4, sizeof(int32), addr numCentroids)
  updateCentroids.bindArgs([gpuAccum, gpuCentroids], [workGroups.int32, centroids.len.int32])

  for _ in 1 .. iterations:
    for i in 0 .. < centroids.len:
      centroids[i].x = points[i].x
      centroids[i].y = points[i].y

    queue.write(addr points[0], gpuPoints, pointSize)
    queue.write(addr centroids[0], gpuCentroids, centroidSize)

    for _ in 1 .. 15:
      queue.run(groupByCluster, points.len)
      queue.run(sumPoints, parallelism, workItems)
      queue.run(updateCentroids, centroids.len)

    var accums = newSeq[Accum](centroids.len * workGroups)

    queue.read(addr points[0], gpuPoints, pointSize)
    queue.read(addr centroids[0], gpuCentroids, centroidSize)
    queue.read(addr accums[0], gpuAccum, accumSize)

  let time = (((cpuTime() - start) * 1000) / float(iterations)).round
  echo format("Made $1 iterations with an average of $2 milliseconds",
              iterations, time)

  for a in centroids:
    echo a

  # Clean up
  check releaseCommandQueue(queue)
  check releaseKernel(groupByCluster)
  check releaseKernel(sumPoints)
  check releaseKernel(updateCentroids)
  check releaseProgram(program)
  check releaseMemObject(gpuPoints)
  check releaseMemObject(gpuCentroids)
  check releaseContext(context)

when isMainModule:
  main()