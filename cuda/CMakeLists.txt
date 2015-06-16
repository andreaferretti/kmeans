# CMakeLists.txt for G4CU project                                                                                                                                                                          
project(kmeans)

# required cmake version                                                                                                                                                                                   
cmake_minimum_required(VERSION 2.8)

# packages                                                                                                                                                                                                 
find_package(CUDA)

file(GLOB SRC "${PROJECT_SOURCE_DIR}/src/*.cu")

# nvcc flags                                                                                                                                                                                               
set(CUDA_NVCC_FLAGS ${CUDA_NVCC_FLAGS};-gencode arch=compute_20,code=sm_20)

message("files: ${SCR}")

cuda_add_executable(driver src/point.cu src/kmeans.cu src/main.cu)
