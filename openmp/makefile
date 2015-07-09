#
# build the openmp project.
#
# usage:
#   normal build:
#   $make
#
#   clean project:
#   $make clean
#   
# @author tmaltempi@gmail.com
# @since 18/06/2015
#

all: make-all
GCC=gcc
GPP=g++
make-all: clean kmeans.o main.o point.o kmeans

kmeans.o: src/kmeans.c
	$(GCC) -Wall -O3 -c ./src/kmeans.c -o ./build/kmeans.o -fopenmp

main.o: src/main.c
	gcc -Wall -O3 -c ./src/main.c -o ./build/main.o -fopenmp

point.o: src/point.c
	$(GCC) -Wall -O3 -c ./src/point.c -o ./build/point.o -fopenmp

kmeans:
	$(GPP) -o kmeans.out ./build/kmeans.o ./build/main.o ./build/point.o -s -ljansson -fopenmp

clean:
	#create a backup of actual kmeans.out if exists
	/bin/sh -c 'if [ ! -d "build" ]; then mkdir build; fi'
	/bin/sh -c 'if [ -f kmeans.out ]; then mv kmeans.out kmeans.out.lastupdated; fi'
	rm -rf ./build/*
