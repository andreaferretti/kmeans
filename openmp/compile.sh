gcc -Wall -O3 -c hashmap.c -o hashmap.o `pkg-config --cflags --libs glib-2.0`
gcc -Wall -O3 -c kmeans.c -o kmeans.o
gcc -Wall -O3 -c main.c -o main.o
gcc -Wall -O3 -c point.c -o point.o
g++  -o kmeans hashmap.o kmeans.o main.o point.o `pkg-config --cflags --libs glib-2.0` -s -ljansson