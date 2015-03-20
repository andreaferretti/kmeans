
public class jsonRead {

	static double[] java_xs = new double[100000];
	static int java_xs_counter = 0;
    static double[] java_ys = new double[100000];
    static int java_ys_counter = 0;


	public static void initialize() {
			try {
			com.fasterxml.jackson.core.JsonFactory factory = new com.fasterxml.jackson.core.JsonFactory();
	        com.fasterxml.jackson.core.JsonParser jp =
	        	factory.createJsonParser(new java.io.File("../points.json"));
			int i = 0;
			while (true) {
				com.fasterxml.jackson.core.JsonToken actual = jp.nextValue();
				while (actual == com.fasterxml.jackson.core.JsonToken.START_ARRAY) {
				actual = jp.nextValue();
			}
			try {
				double x = jp.getDoubleValue();
				jp.nextToken();
				double y = jp.getDoubleValue();
				java_xs[i] = x;
				java_ys[i] = y;
				i++;
			} catch(Exception ex) {
			//ex.printStackTrace();
			System.out.println("read "+i+" points");
			break;
			}
			actual = jp.nextToken();
			while (jp.nextToken() == com.fasterxml.jackson.core.JsonToken.END_ARRAY) {
				actual = jp.nextToken();
			}
			}
			jp.close();
			} catch (Exception err) {
			err.printStackTrace();
			}
	}

	public static double getNextX() {
		return java_xs[java_xs_counter++];
	}

	public static double getNextY() {
		return java_ys[java_ys_counter++];
	}

}