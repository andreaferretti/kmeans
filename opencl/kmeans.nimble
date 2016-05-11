mode = ScriptMode.Verbose

packageName   = "kmeans-opencl"
version       = "0.1.0"
author        = "Andrea Ferretti"
description   = "OpenCL experiment"
license       = "Apache2"

requires "nim >= 0.13.0", "opencl >= 1.0"

template dependsOn*(task: untyped): stmt =
  exec "nimble " & astToStr(task)

task headers, "compile headers with c2nim":
  exec "c2nim point.h"

task kmeans, "run kmeans example":
  dependsOn headers
  switch("cincludes", "/usr/local/cuda/targets/x86_64-linux/include")
  switch("clibdir", "/usr/local/cuda/targets/x86_64-linux/lib")
  --define: release
  --path: "."
  --run
  setCommand "c", "kmeans"