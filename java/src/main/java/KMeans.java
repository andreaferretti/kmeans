
import java.util.Collection;
import java.util.HashMap;

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
	
	private Point average(Point[] xs) {
		Point p = new Point(0,0);
		int l = xs.length;
		for (int i=0;i<l;i++) {
			p.addToThis(xs[i]);
		}
		p.divideToThis(l);
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
	
	private Collection<Point[]> clusters() {
		HashMap<Point, Point[]> hm = new HashMap<Point, Point[]>();
		for (int i=0;i<Xs.length; i++) {
			Point Key = closest(Xs[i], Centroids);
			Point[] old = hm.get(Key);
			int lenght = 1;
			if (old!=null) {
				lenght = old.length+1;
			}
			Point[]	newer = new Point[lenght];
			newer[lenght-1] = Xs[i];
			if (old==null) {
				hm.put(Key, newer);
			}
		}
		return hm.values();
	}
	
	public Collection<Point[]> run() {
		Centroids = new Point[n];
		for(int i=0;i<n;i++)
			Centroids[i] = Xs[i];
		
		for(int i=0;i<iters;i++) {
			Collection<Point[]> clusters = clusters();
			int k = 0;
			for (Point[] cluster: clusters) {
				Centroids[k] = average(cluster);
				k++;
			}
		}
		return clusters();
	}

}
