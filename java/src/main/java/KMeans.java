
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class KMeans {

	private int n = 10;
	private int iters = 15;

	private Point[] Xs;
	private Point[] Centroids;

	public KMeans(Point[] Xs, int n, int iters) {
		this.Xs = Xs;
		this.n = n;
		this.iters = iters;
	}

	public KMeans(Point[] Xs) {
		this.Xs = Xs;
	}

	private double dist(Point p1, Point p2) {
		return p1.sub(p2).modulus();
	}

	private Point average(ArrayList<Point> xs) {
		double size = xs.size();
		Iterator<Point> iter = xs.iterator();
		Point p = new Point(0,0);
		while (iter.hasNext()) {
			p.addToThis(iter.next());
		}
		p.divideToThis(size);
		return p;
	}

	private Point closest(Point x, Point[] choices) {
		double minVal = dist(x, choices[0]);
		int min = 0;
		for (int i=1;i<choices.length;i++) {
			double actualDist = dist(x, choices[i]);
			if (actualDist < minVal) {
				min = i;
				minVal = actualDist;
			}
		}
		return choices[min];
	}

	private Collection<ArrayList<Point>> clusters() {
		HashMap<Point, ArrayList<Point>> hm = new HashMap<Point, ArrayList<Point>>();
		for (int i=0;i<Xs.length; i++) {
			Point Key = closest(Xs[i], Centroids);
			ArrayList<Point> alp = hm.get(Key);
			if (alp == null)
				alp = new ArrayList<Point>();
			alp.add(Xs[i]);
			hm.put(Key, alp);
		}
		return hm.values();
	}

	public Collection<ArrayList<Point>> run() {
		Centroids = new Point[n];
		for(int i=0;i<n;i++)
			Centroids[i] = Xs[i];

		for(int i=0;i<iters;i++) {
			Collection<ArrayList<Point>> clusters = clusters();
			int k = 0;
			for (ArrayList<Point> cluster: clusters) {
				Centroids[k] = average(cluster);
				k++;
			}
		}
		return clusters();
	}

}

