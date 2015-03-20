public struct RichPoint {
	val x : Double; 
	val y:Double;
	
	def this(x:Double, y:Double) {
		this.x = x; this.y = y;
	}
	
	operator this + (p2: RichPoint): RichPoint = new RichPoint(x+p2.x,y+p2.y);
	
	operator this - (p2: RichPoint): RichPoint = new RichPoint(x-p2.x,y-p2.y);
	
	operator this / (d: Double): RichPoint = new RichPoint(x/d,y/d);
	
	def modulus(): Double = Math.sqrt( KMeans.sq(x) + KMeans.sq(y));
}
