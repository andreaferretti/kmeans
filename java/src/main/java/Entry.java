import java.io.File;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class Entry {
	
	static int times = 100;

	public static void main(String[] args) throws Exception {
		JsonFactory factory = new JsonFactory();
		JsonParser jp = factory.createJsonParser(new File("../points.json"));
		
		Point[] Xs = new Point[100000];
		int i = 0;
		while (true) {
			while (jp.nextToken() == JsonToken.START_ARRAY || 
				   jp.nextToken() == JsonToken.END_ARRAY) {}
				try {
					double x = jp.getDoubleValue();
					double y = jp.getDoubleValue();
					Xs[i] = new Point(x,y);
					i++;
				} catch(Exception ex) {
					//ex.printStackTrace();
					break;
				}
			while (jp.nextToken() == JsonToken.START_ARRAY || 
			  	   jp.nextToken() == JsonToken.END_ARRAY) {}
		}
		jp.close();
		
		KMeans kmeans = new KMeans(Xs);
		long totalTime = 0;
		for (int k=0;k<times;k++) {
			totalTime += executionTime(kmeans);
		}
		System.out.println("Average time "+(totalTime/times));
	}
	
	private static long executionTime(KMeans kmeans) {
		long timeBefore = System.currentTimeMillis();
		kmeans.run();
		long timeAfter = System.currentTimeMillis();
		return (timeAfter - timeBefore);
	}

}
