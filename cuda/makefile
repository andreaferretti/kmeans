#/usr/local/cuda-7.0/bin/nvcc --cudart static --relocatable-device-code=false -gencode arch=compute_35,code=compute_35 -gencode arch=compute_35,code=sm_35 -link -o  "t9-histogram" $(OBJS) $(USER_OBJS) $(LIBS)
#
#hashmap.c  hashmap.h  kmeans.c  kmeans.h  main.cu  point.c  point.h
#
#gcc -Wall -O3 -c main.c -o main.o
#gcc -Wall -O3 -c point.c -o point.o
#g++  -o kmeans hashmap.o kmeans.o main.o point.o `pkg-config --cflags --libs glib-2.0` -s -ljansson



all: make-all

make-all: hashmap.o kmeans.o main.o point.o kmeans

hashmap.o: src/hashmap.c
	/usr/local/cuda-7.0/bin/nvcc --compile -o ./build/hashmap.o ./src/hashmap.c `pkg-config --cflags --libs glib-2.0`

kmeans.o: src/kmeans.c
	/usr/local/cuda-7.0/bin/nvcc --compile -o ./build/kmeans.o ./src/kmeans.c

main.o: src/main.cu
	/usr/local/cuda-7.0/bin/nvcc --compile -o ./build/main.o ./src/main.cu

point.o: src/point.c
	/usr/local/cuda-7.0/bin/nvcc --compile -o ./build/point.o ./src/point.c

kmeans:
	/usr/local/cuda-7.0/bin/nvcc "build/hashmap.o" "build/kmeans.o" "build/main.o" "build/point.o" `pkg-config --cflags --libs glib-2.0` -ljansson -o kmeans.out

clean:
	rm -rf build/*

