
public class Point {
	double x = 0;
	double y = 0;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Point divide(double d) {
		return new Point(x/d,y/d);
	}

	public void divideToThis(double d) {
		x = x/d;
		y = y/d;
		return;
	}

	public Point add(Point p2) {
		return new Point(x + p2.x, y + p2.y);
	}

	public void addToThis(Point p2) {
		x = x + p2.x;
		y = y + p2.y;
		return;
	}

	public Point sub(Point p2) {
		return new Point(x - p2.x, y - p2.y);
	}

	private double sq(double x) {
		return x*x;
	}

	public double modulus() {
		return Math.sqrt(sq(x) + sq(y));
	}

	@Override public String toString() {
		return "(" + x + "," + y + ")";
	}
}
