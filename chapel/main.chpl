
const n: int = 10;
const iters: int = 15;

const executions: int = 100;

record point {
	var x: real;
	var y: real;

	proc divide(d: real) {
		return new point(x = x/d, y = y/d);
	}
	
	proc add(p2: point) {
		return new point(x = x+p2.x, y = y+p2.y);
	}

	proc sub(p2: point) {
		return new point(x = x-p2.x, y = y-p2.y);
	}

	proc modulus() {
		return sqrt(sq(x)+sq(y));
	}

}

class closestPoint : ReduceScanOp {
	type eltType;
	var min : point;
	var minDist: real = max(real);

	proc accumulate((val, p): (point, point)) {
		var actualDist = dist(p, val);
		if (actualDist < minDist) {
			minDist = actualDist;
			min = val;
		}
	}

	proc combine(other: closestPoint){
		if (this.minDist > other.minDist) {
			this.min = other.min;
			this.minDist = other.minDist;
		}
	}

	proc generate(){
		return min;
	}
}

proc sq(x: real) {
	return x*x;
}

proc dist(p1: point, p2: point) {
	return (p1.sub(p2)).modulus();
}

proc average(ps: domain(point)) {
	var sum = new point(0,0);
	forall e in ps do 
		sum = sum.add(e);
	return sum.divide(ps.size);
}

proc closest(rp: point, choices: [] point): point {
	//Cannot use a reduce operator instatiating it with a parameter?
	return closestPoint reduce forall x in choices do (x,rp);
}

proc clusters(xs: [] point, centroids: [] point) {
	//too tricky hashmap construction...
	type PointArr = domain(point);
	var keys: domain(point);
	var hashMap: [keys] PointArr;

	forall c in centroids do
		keys += c;

	forall x in xs do 
		hashMap(closest(x, centroids)) += x;

	var result: [1..centroids.size] PointArr;
	forall i in 1..centroids.size do {
		result[i] += hashMap(centroids(i));
	}

	return result;
}

var centroids: [1..n] point;

var xs: [1..100000] point;

C.getJson();

for i in 1..100000 do
	xs[i] = new point(C.getXAt(i), C.getYAt(i));

forall i in 1..centroids.size do
	centroids[i] = xs[i];

use Time;
var timer = new Timer();

timer.clear();
timer.start();

for k in 1..executions do {

	for i in 1..iters do {
		var clus = clusters(xs, centroids);
		forall c in [1..clus.size] do
			centroids[c] = average(clus[c]);
	}

}

timer.stop();

var elapsedTime = timer.elapsed(TimeUnits.milliseconds);

writeln("Last centroids are:");
for c in centroids do
	writeln(c);

writeln("Elapsed time is ", (elapsedTime/executions));

module C {
    extern {
		#include <stdio.h>
		#include <stdlib.h>
		#include <string.h>
		#include <jansson.h>

		static double cxs[100000];
   		static double cys[100000];
   		

      static void getJson() { 
      	int i=0;
   		json_t *json;
   		json_error_t error;
   		size_t index;
   		long int temp = 0;
   		json_t *value;

   		
   		json = json_load_file("/home/andrea/workspace/kmeans/points.json", 0, &error);
   		if(!json) {
        	printf("Error parsing Json file");
        	fflush(stdout);
        	return;
    	}

    	json_array_foreach(json, index, value) {
        	double x = json_number_value(json_array_get(value,0));
        	double y = json_number_value(json_array_get(value,1));
        	cxs[index] = x;
        	cys[index] = y;
    	}

      	return; 
      }

      static double getXAt(long i) {
      	return cxs[i-1];
      }
      static double getYAt(long i) {
      	return cys[i-1];
      }
    }
}