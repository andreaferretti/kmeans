package com.example;

/**
 * Created by evacchi on 28/02/15.
 */
class Point {
    double x,y;

    public Point(double x, double y) {
        this.x = x; this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Point plus(Point p2) {
        return new Point(x + p2.getX(), y + p2.getY());
    }
    public Point minus(Point p2) {
        return new Point(x - p2.getX(), y - p2.getY());
    }
    public Point div(double d) {
        return new Point(x/d, y/d);
    }
    public Double getModulus() { return Math.sqrt(sq(x) + sq(y)); }
    private double sq(double x) { return x*x; }

    @Override
    public String toString() {
        return "(" +x+", "+y+")";
    }
}