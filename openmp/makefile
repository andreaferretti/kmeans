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
make-all: clean hashmap.o kmeans.o main.o point.o kmeans

hashmap.o: src/hashmap.c
	$(GCC) -Wall -O3 -c ./src/hashmap.c -o ./build/hashmap.o `pkg-config --cflags --libs glib-2.0`

kmeans.o: src/kmeans.c
	$(GCC) -Wall -O3 -c ./src/kmeans.c -o ./build/kmeans.o

main.o: src/main.c
	gcc -Wall -O3 -c ./src/main.c -o ./build/main.o

point.o: src/point.c
	$(GCC) -Wall -O3 -c ./src/point.c -o ./build/point.o

kmeans:
	$(GPP) -o kmeans ./build/hashmap.o ./build/kmeans.o ./build/main.o ./build/point.o `pkg-config --cflags --libs glib-2.0` -s -ljansson

clean:
	#create a backup of actual kmeans.out if exists
	/bin/sh -c 'if [ -f kmeans.out ]; then mv kmeans.out kmeans.out.lastupdated; fi'
	rm -rf ./build/*
