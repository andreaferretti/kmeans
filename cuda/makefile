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
NVCC=nvcc
make-all: clean kmeans.o main.o point.o kmeans

point.o: src/point.cu
	$(NVCC) --compile -o ./build/point.o ./src/point.cu -std=c++11 -dc

kmeans.o: src/kmeans.cu
	$(NVCC) --compile -o ./build/kmeans.o ./src/kmeans.cu -std=c++11 -dc

main.o: src/main.cu
	$(NVCC) --compile -o ./build/main.o ./src/main.cu -std=c++11 -dc

kmeans:
	$(NVCC) "build/kmeans.o" "build/main.o" "build/point.o" -ljansson -o kmeans.out 

clean:
	#create a backup of actual kmeans.out if exists
	/bin/sh -c 'if [ ! -f ./build/ ]; then mkdir build; fi'
	/bin/sh -c 'if [ -f kmeans.out ]; then mv kmeans.out kmeans.out.lastupdated; fi'
	rm -rf ./build/*
