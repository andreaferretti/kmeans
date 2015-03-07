package main

import ("fmt"; "math"; "encoding/json"; "time"; "io/ioutil")


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
	for i:=0; i < len(points); i++ {
		tmp = *tmp.add(&points[i])
	}
	tmp = *tmp.divide(float64(len(points)))
	return &tmp
}

func closest(p *Point, choices []Point) *Point {
	min := 0
	minDist := dist(p, &choices[0])
	for i:=1; i < len(choices); i++ {
		actualDist := dist(p, &choices[i])
		if (minDist > actualDist) {
			min = i
			minDist = actualDist
		}
	} 
	return &choices[min]
}

func clusters(xs []Point, centroids []Point) [][]Point {
	hm := make(map[Point][]Point)
	for i:=0; i < len(xs); i++ {
		theClosest := *closest(&xs[i], centroids)
		//fmt.Println(theClosest)
		hm[theClosest] = append(hm[theClosest], xs[i])
	}
	result := [][]Point {}

	for _, val := range hm {
	  result = append(result, val)
  	}
	return result;
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
	centroids := []Point{}

    for i:=0; i < len(*res); i++ {
		xs = append(xs, Point{(*res)[i][0],(*res)[i][1]})
	}

	for i:=0; i < n; i++ {
			centroids = append(centroids, xs[i])
	}

    before := time.Now()

    for ex:=0; ex < executions; ex++ {
		
    	for i:=0; i < n; i++ {
			centroids[i] = xs[i]
		}

		for k:=0; k < iters; k++ {
			clus := clusters(xs, centroids)
			for i:=0; i < n; i++ {
				centroids[i] = *average(clus[i])
			}
		}

	}

	after := time.Now()

    fmt.Println("Final Centroids are ")
		for i:=0; i < n; i++ {
			fmt.Println(centroids[i])
	}

    time := (after.Sub(before).Nanoseconds())/int64(1000000)/int64(executions)

    fmt.Println("Average time is ",time)
}