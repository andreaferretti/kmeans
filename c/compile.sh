#enabling hashmap -DWITHHASHMAP
#sudo apt-get install libjansson-dev
gcc -Wall -O3 -c hashmap.c -o hashmap.o
gcc -Wall -O3 -c kmeans.c -o kmeans.o
gcc -Wall -O3 -c main.c -o main.o
gcc -Wall -O3 -c point.c -o point.o
g++  -o kmeans hashmap.o kmeans.o main.o point.o  -s -ljansson  
