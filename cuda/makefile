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

make-all: clean hashmap.o kmeans.o main.o point.o kmeans

hashmap.o: src/hashmap.c
	/usr/local/cuda-7.0/bin/nvcc --compile -o ./build/hashmap.o ./src/hashmap.c `pkg-config --cflags --libs glib-2.0`

point.o: src/point.c
	/usr/local/cuda-7.0/bin/nvcc --compile -o ./build/point.o ./src/point.c

kmeans.o: src/kmeans.cu
	/usr/local/cuda-7.0/bin/nvcc --compile -o ./build/kmeans.o ./src/kmeans.cu

main.o: src/main.cu
	/usr/local/cuda-7.0/bin/nvcc --compile -o ./build/main.o ./src/main.cu

kmeans:
	/usr/local/cuda-7.0/bin/nvcc "build/hashmap.o" "build/kmeans.o" "build/main.o" "build/point.o" `pkg-config --cflags --libs glib-2.0` -ljansson -o kmeans.out

clean:
	#create a backup of actual kmeans.out if exists
	/bin/sh -c 'if [ -f kmeans.out ]; then mv kmeans.out kmeans.out.lastupdated; fi'
	rm -rf ./build/*

