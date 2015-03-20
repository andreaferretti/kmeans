import x10.regionarray.Array;
import x10.util.HashMap;
import x10.util.ArrayList;
import x10.util.RailUtils;

public class KMeans {
	
	static def sq(x: Double): Double = 
		x*x;
	
	static def dist(p1: RichPoint, p2: RichPoint) = 
		(p1-p2).modulus();
	
	static def average(xs: ArrayList[RichPoint]) = 
		RailUtils.reduce(xs.toRail(),(p1:RichPoint,p2:RichPoint)=>p1+p2, new RichPoint(0,0))/xs.size();
	
	static def closest(p: RichPoint, choices: ArrayList[RichPoint]): RichPoint { 
		struct WPoint {
			val p : RichPoint; 
			val dist :Double;
			val valid: Boolean;
			
			def this(p: RichPoint, dist: Double, valid: Boolean) {
				this.p =p;
				this.dist = dist;
				this.valid = valid;
			}
		}
				
		val dists = new Rail[WPoint](choices.size());
		
		val pointDist = (x : RichPoint) => new WPoint(x,dist(x, p), true);
		RailUtils.map(choices.toRail(),dists, pointDist); 
		
		val reduceTask = (wp1: WPoint, wp2: WPoint) => {
			if (!wp1.valid) return wp2;
			else if (!wp2.valid) return wp1;
			else {
				if (wp1.dist < wp2.dist) return wp1;
				else return wp2;
			}
		};
		
		val unity = new WPoint(new RichPoint(0,0),0.0, false);
		
		return RailUtils.reduce(dists, reduceTask, unity).p;
	}
	
	static def clusters(xs: Array[RichPoint], centroids: ArrayList[RichPoint]): Rail[ArrayList[RichPoint]] {
		
		val hm = new HashMap[RichPoint, ArrayList[RichPoint]](centroids.size());
		for (p in xs) {
			val key = closest(xs(p), centroids);
			val value = hm.getOrElse(key, new ArrayList[RichPoint]());
			value.add(xs(p));
			hm.put(key, value);
		}
		
		val dest = new Rail[ArrayList[RichPoint]](centroids.size());
		RailUtils.map(centroids.toRail(), dest, (c: RichPoint) => hm.get(c));
		return dest;
	}
	
	static val n = 10;
	static val iters = 15;
	
	static def run(xs: Array[RichPoint]) {
		val centroids = new ArrayList[RichPoint](n);
		
		for (i in 0..(n-1)) centroids(i) = xs(i);
		
		for (k in 1..(iters)) {
			val clus = clusters(xs, centroids);
			val clusIter = clus.iterator();
			for (i in 0..(n-1)) {
				centroids(i) = average(clusIter.next());
			}
		}
		
		Console.OUT.println("Centroids are:");
		for (i in 0..(n-1)) {
			Console.OUT.println("Point("+centroids(i).x+" , "+centroids(i).y+")");
		}
		
		return clusters(xs, centroids);
	}
	
}