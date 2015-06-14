# CMakeLists.txt for G4CU project                                                                                                                                                                          
project(kmeans)

# required cmake version                                                                                                                                                                                   
cmake_minimum_required(VERSION 2.8)

# packages                                                                                                                                                                                                 
find_package(CUDA)

aux_source_directory(src SRC)

# nvcc flags                                                                                                                                                                                               
set(CUDA_NVCC_FLAGS ${CUDA_NVCC_FLAGS};-gencode arch=compute_20,code=sm_20)

cuda_add_executable(driver ${SRC})
