package main

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"math"
	"time"
)


func sq(x float64) float64 {
	return x * x
}

type Point struct {
	x, y float64
}

func (p Point) add(p2 Point) Point {
	return Point{p.x + p2.x, p.y + p2.y}
}
func (p Point) sub(p2 Point) Point {
	return Point{p.x - p2.x, p.y - p2.y}
}
func (p Point) divide(d float64) Point {
	return Point{p.x / d, p.y / d}
}
func (p Point) modulus() float64 {
	return math.Sqrt(sq(p.x) + sq(p.y))
}

func dist(p1 Point, p2 Point) float64 {
	return (p1.sub(p2)).modulus()
}

func average(points []Point) Point {
	tmp := Point{0, 0}
	for _, point := range points {
		tmp = tmp.add(point)
	}
	tmp = tmp.divide(float64(len(points)))
	return tmp
}

func closest(p Point, choices []Point) int {
	min := 0
	minDist := math.MaxFloat64
	for i, choice := range choices {
		actualDist := dist(p, choice)
		if minDist > actualDist {
			min = i
			minDist = actualDist
		}
	}
	return min
}

func clusters(xs []Point, centroids []Point) [][]Point {
	hm := make([][]Point, len(centroids))
	for _, x := range xs {
		theClosest := closest(x, centroids)
		hm[theClosest] = append(hm[theClosest], x)
	}
	i := 0
	for i < len(hm) {
		if len(hm[i]) > 0 {
			i++
		} else {
			hm = append(hm[:i], hm[i+1:]...)
		}
	}
	return hm
}

func run(n int, iters int, xs []Point) [][]Point {
	centroids := make([]Point, n)
	copy(centroids, xs)

	for k := 0; k < iters; k++ {
		clus := clusters(xs, centroids)
		for i := 0; i < n; i++ {
			centroids[i] = average(clus[i])
		}
	}

	/*
	fmt.Println("Final Centroids are ")
	for _, centroid := range centroids {
		fmt.Println(centroid)
	}*/

	return clusters(xs, centroids)
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

	for _, res := range *res {
		xs = append(xs, Point{res[0], res[1]})
	}

	before := time.Now()

	for ex := 0; ex < executions; ex++ {
		run(n, iters, xs)
	}

	after := time.Now()

	time := (after.Sub(before).Nanoseconds()) / int64(1000000) / int64(executions)

	fmt.Println("Average time is ", time)
}
