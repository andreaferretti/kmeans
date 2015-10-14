# CMakeLists.txt for G4CU project                                                                                                                                 

project(kmeans)

# required cmake version

cmake_minimum_required(VERSION 2.8)

# packages

find_package(CUDA)

# nvcc flags

set(CUDA_SEPARABLE_COMPILATION ON)

cuda_add_executable(kmeans.out src/point.cu src/kmeans.cu src/main.cu)
target_link_libraries(kmeans.out jansson)
