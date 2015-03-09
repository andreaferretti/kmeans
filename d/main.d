import std.stdio;
import std.math;
import std.json;
import std.file;
import std.datetime;

class Point
{
	double x;
	double y;

	this(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    Point add(Point p2)
    {
    	return new Point(x+p2.x, y+p2.y);
    }

    Point sub(Point p2)
    {
    	return new Point(x-p2.x, y-p2.y);
    }

    Point divide(double d)
    {
    	return new Point(x/d, y/d);
    }

    double modulus()
    {
    	return sqrt(sq(x) + sq(y));
    }
    
}

auto sq = (double x) => x*x;

auto dist = (Point p1, Point p2) => p1.sub(p2).modulus();

Point average(Point[] points)
{
    auto ret = new Point(0,0);
    for (int i=0 ; i<points.length; i ++) {
        ret = ret.add(points[i]);
    }
    return ret.divide(points.length);
}

Point closest(Point p, Point[] choices)
{
    int min = 0;
    double minDist = dist(p, choices[0]);
    for (int i=0 ; i<choices.length; i ++) {
        double actualDist = dist(p, choices[i]);
        if (actualDist < minDist) {
            min = i;
            minDist = actualDist;
        }
    }
    return choices[min];
}

Point[][] clusters(Point[] xs, Point[] centroids)
{
    Point[][Point] hm;   
    for (int i=0;i<xs.length;i++) {
        auto theClosest = closest(xs[i], centroids);
        hm[theClosest] ~= [xs[i]];
    }
    return hm.values();
}

Point[][] run(int n, int iters, Point[] xs)
{
    Point[] centroids = xs[0 .. n];
    for (int i=0;i<iters;i++) {
        auto clus = clusters(xs, centroids);
        for (int k=0;k<n;k++) {
            centroids[k] = average(clus[k]);
        }

/*
        writeln("Final Centroids:",i);
            for (int k=0;k<n;k++) {
                writeln("Point(", centroids[k].x, " , ", centroids[k].y, ")");
        }
*/
    }

    return clusters(xs, centroids);
}

void main()
{
    int n= 10;
    int iters= 20;

    int executions = 1;

    Point xs[100000];

    auto file = cast(char[])read("../points.json");
    JSONValue json = parseJSON(file);

    JSONValue[] values = json.array();
    for (int i=0;i<values.length;i++) {
        auto elem = values[i].array();
        xs[i] = new Point(elem[0].floating,elem[1].floating);
    }

    auto before = Clock.currTime();

    for (int i=0;i<executions;i++) {
        run(n, iters, xs);
    }
    
    auto after = Clock.currTime();

    auto totalTime = (after.stdTime() - before.stdTime())/10000/executions;

    writeln("Average Time is: ", totalTime);
}