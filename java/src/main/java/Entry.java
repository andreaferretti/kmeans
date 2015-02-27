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
			JsonToken actual = jp.nextValue();
			while (actual == JsonToken.START_ARRAY) {
				actual = jp.nextValue();
			}
				try {
					double x = jp.getDoubleValue();
					jp.nextToken();
					double y = jp.getDoubleValue();
					Xs[i] = new Point(x,y);
					i++;
				} catch(Exception ex) {
					//ex.printStackTrace();
					break;
				}
			actual = jp.nextToken();
			while (jp.nextToken() == JsonToken.END_ARRAY) {
				actual = jp.nextToken();
			}
		}
		jp.close();

		KMeans kmeans = new KMeans(Xs);
		long totalTime = 0;
		long timeBefore = System.currentTimeMillis();
		for (int k=0;k<times;k++) {
			kmeans.run();
		}
		long timeAfter = System.currentTimeMillis();
		totalTime = timeAfter -timeBefore;
		System.out.println("Average time "+(totalTime/times));
	}

	private static void executionTime(KMeans kmeans) {
		kmeans.run();
		return;
	}

}

