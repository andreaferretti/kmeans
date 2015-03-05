const n: int = 10;
const iters: int = 15;

const executions: int = 100;

class point {
	var x: real;
	var y: real;

	proc divide(d: real) {
		this.x = x/d;
		this.y = y/d;
		return;
	}
	
	proc add(p2: point) {
		this.x = this.x+p2.x;
		this.y = this.y+p2.y;
		return;
	}

	proc sub(p2: point) {
		this.x = this.x-p2.x;
		this.y = this.y-p2.y;
		return;
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
	var tmp: point = new point(p1.x, p1.y);
	tmp.sub(p2);
	var result = tmp.modulus();
	delete(tmp);
	return result;
}

proc average(ps: domain(point)): point {
	var sum: point = new point(0,0);
	forall e in ps do 
		sum.add(e);
	sum.divide(ps.size);
	return sum;
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

use Time;
var timer = new Timer();

timer.clear();
timer.start();

for k in 1..executions do {
	
	forall j in 1..n do {
		delete(centroids[j]);
		centroids[j] = new point(xs[j].x, xs[j].y);
	}

	for i in 1..iters do {
		var clus = clusters(xs, centroids);
		forall c in [1..n] do {
			centroids[c] = average(clus[c]);
		}
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

   		
   		json = json_load_file("../points.json", 0, &error);
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
