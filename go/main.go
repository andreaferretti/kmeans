package main

import ("fmt"; 
		"math"; 
		"encoding/json";	
		"time";	
		"io/ioutil")


type Point struct {
	x,y float64
}

func (p *Point) add(p2 *Point) *Point {
	return &Point{p.x+p2.x, p.y+p2.y}
}
func (p *Point) sub(p2 *Point) *Point {
	return &Point{p.x-p2.x, p.y-p2.y}
}
func (p *Point) divide(d float64) *Point {
	return &Point{p.x/d, p.y/d}
}
func (p *Point) modulus() float64 {
	return math.Sqrt(sq(p.x) + sq(p.y))
}



func sq(x float64) float64 {
	return x*x
}

func dist(p1 *Point, p2 *Point) float64 {
	return (p1.sub(p2)).modulus()
}

func average(points []Point) *Point {
	tmp := Point{0,0}
	for i := range points {
		tmp = *tmp.add(&points[i])
	}
	tmp = *tmp.divide(float64(len(points)))
	return &tmp
}

func closest(p *Point, choices []Point) *Point {
	min := 0
	minDist := 0.0
	for i:= range choices {
		actualDist := dist(p, &choices[i])
		if (i ==  0 || minDist > actualDist) {
			min = i
			minDist = actualDist
		}
	} 
	return &choices[min]
}

func clusters(xs []Point, centroids []Point) [][]Point {
	hm := make(map[Point][]Point)
	for i := range xs {
		theClosest := *closest(&xs[i], centroids)
		hm[theClosest] = append(hm[theClosest], xs[i])
	}
	result := [][]Point {}

	for _, val := range hm {
	  result = append(result, val)
  	}
	return result;
}

func run(n int,iters int, xs []Point) [][]Point {
	centroids := []Point{}

	for i:=0; i < n; i++ {
		centroids = append(centroids, xs[i])
	}
    
	for k:=0; k < iters; k++ {
		clus := clusters(xs, centroids)
		for i:=0; i < n; i++ {
			centroids[i] = *average(clus[i])
		}
	}

	/*fmt.Println("Final Centroids are ")
		for i:=0; i < n; i++ {
			fmt.Println(centroids[i])
	}*/

	return clusters(xs, centroids);
}

func main() {

	executions := 100

	iters := 15
	n := 10

	dat, err := ioutil.ReadFile("../points.json")
    if err != nil {
        fmt.Println("Error reading file ")
        panic(err)
    }

	res := &[][]float64{}
    json.Unmarshal(dat, &res)

	xs := []Point{}
	
    for i:= range (*res) {
		xs = append(xs, Point{(*res)[i][0],(*res)[i][1]})
	}

    before := time.Now()

    for ex:=0; ex < executions; ex++ {
		run(n,iters,xs);
	}

	after := time.Now()

    time := (after.Sub(before).Nanoseconds())/int64(1000000)/int64(executions)

    fmt.Println("Average time is ",time)
}
