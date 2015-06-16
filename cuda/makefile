#
# build the cuda project.
#
# usage:
#   normal build:
#   $make
#
#   clean project:
#   $make clean
#   
# @author tmaltempi@gmail.com
# @since 05/06/2015
#

all: make-all

make-all: clean kmeans.o main.o point.o kmeans

point.o: src/point.cu
	/usr/local/cuda-7.0/bin/nvcc --compile -o ./build/point.o ./src/point.cu -std=c++11 -dc

kmeans.o: src/kmeans.cu
	/usr/local/cuda-7.0/bin/nvcc --compile -o ./build/kmeans.o ./src/kmeans.cu -std=c++11 -dc

main.o: src/main.cu
	/usr/local/cuda-7.0/bin/nvcc --compile -o ./build/main.o ./src/main.cu -std=c++11 -dc

kmeans:
	/usr/local/cuda-7.0/bin/nvcc "build/kmeans.o" "build/main.o" "build/point.o" -ljansson -o kmeans.out 

clean:
	#create a backup of actual kmeans.out if exists
	/bin/sh -c 'if [ -f kmeans.out ]; then mv kmeans.out kmeans.out.lastupdated; fi'
	rm -rf ./build/*

