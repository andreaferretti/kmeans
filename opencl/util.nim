import opencl

type
  PlatformNotFound = object of Exception
  DeviceNotFound = object of Exception

proc newPlatformNotFound(): ref PlatformNotFound =
  new result
  result.msg = "PlatformNotFound"

proc newDeviceNotFound(): ref DeviceNotFound =
  new result
  result.msg = "DeviceNotFound"

proc name*(id: PPlatformId): string =
  var size = 0
  check getPlatformInfo(id, PLATFORM_NAME, 0, nil, addr size)
  result = newString(size)
  check getPlatformInfo(id, PLATFORM_NAME, size, addr result[0], nil)


proc maxWorkGroups*(id: PDeviceId): int =
  check getDeviceInfo(id, DEVICE_MAX_WORK_GROUP_SIZE, sizeof(int), addr result, nil)


proc getPlatformByName*(platformName: string): PPlatformId =
  var numPlatforms: uint32
  check getPlatformIDs(0, nil, addr numPlatforms)
  var platforms = newSeq[PPlatformId](numPlatforms)
  check getPlatformIDs(numPlatforms, addr platforms[0], nil)

  for platform in platforms:
    if platform.name.substr(0, platformName.high) == platformName:
      return platform

  raise newPlatformNotFound()

proc getDevices*(platform: PPlatformId): seq[PDeviceId] =
  var numDevices: uint32
  check getDeviceIDs(platform, DEVICE_TYPE_ALL, 0, nil, addr numDevices)
  if numDevices == 0:
    raise newDeviceNotFound()

  var devices = newSeq[PDeviceId](numDevices)
  check getDeviceIDs(platform, DEVICE_TYPE_ALL, numDevices, addr devices[0], nil)
  devices

proc createContext*(devices: seq[PDeviceId]): PContext =
  var status: TClResult
  var devs = devices
  result = createContext(nil, devs.len.uint32, cast[ptr PDeviceId](addr devs[0]), nil, nil, addr status)
  check status

proc createProgram*(context: PContext, body: string): PProgram =
  var status: TClResult
  var lines = [cstring(body)]
  result = createProgramWithSource(context, 1, cast[cstringArray](addr lines), nil, addr status)
  check status

proc buildOn*(program: PProgram, devices: seq[PDeviceId]) =
  var devs = devices
  check buildProgram(program, devs.len.uint32, cast[ptr PDeviceId](addr devs[0]), nil, nil, nil)

proc commandQueueFor*(context: PContext, device: PDeviceId): PCommandQueue =
  var status: TClResult
  result = createCommandQueue(context, device, 0, addr status)
  check status

proc createKernel*(program: PProgram, name: string): PKernel =
  var status: TClResult
  result = createKernel(program, name, addr status)
  check status

proc bindArgs*(kernel: PKernel, buffers: openarray[PMem], ints: openarray[int32]) =
  var bufs = @buffers
  var intBufs = @ints
  for i in 0 .. < bufs.len:
    check setKernelArg(kernel, i.uint32, sizeof(Pmem), addr bufs[i])
  for i in 0 .. < intBufs.len:
    check setKernelArg(kernel, (bufs.len + i).uint32, sizeof(int32), addr intBufs[i])

proc run*(queue: PCommandQueue, kernel: PKernel, parallelism: int) =
  var globalWorkSize = [parallelism, 0, 0]
  check enqueueNDRangeKernel(queue, kernel, 1, nil,  cast[ptr int](addr globalWorkSize), nil, 0, nil, nil)

proc run*(queue: PCommandQueue, kernel: PKernel, totalWork, localWork: int) =
  var
    globalWorkSize = [totalWork, 0, 0]
    localWorkSize = [localWork, 0, 0]
  check enqueueNDRangeKernel(queue, kernel, 1, nil,  cast[ptr int](addr globalWorkSize), cast[ptr int](addr localWorkSize), 0, nil, nil)

proc write*(queue: PCommandQueue, src: pointer, dest: PMem, size: int) =
  check enqueueWriteBuffer(queue, dest, CL_FALSE, 0, size, src, 0, nil, nil)

proc read*(queue: PCommandQueue, dest: pointer, src: PMem, size: int) =
  check enqueueReadBuffer(queue, src, CL_TRUE, 0, size, dest, 0, nil, nil)