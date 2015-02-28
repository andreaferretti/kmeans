package com.example;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;

import java.io.File;
import java.text.MessageFormat;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by evacchi on 28/02/15.
 */
public class Main {

    private List<Point> readPoints(String path) throws Exception {
        JsonParser json = new JsonFactory().createParser(new File(path));
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<List<Double>>> typeReference = new TypeReference<List<List<Double>>>(){};
        return mapper.<List<List<Double>>>readValue(json, typeReference).stream().map( x -> new Point(x.get(0), x.get(1)) ).collect(toList());
    }

    public void main() throws Exception {
        final int iterations = 100;
        final List<Point> points = readPoints("../points.json");
        final KMeans kMeans = new KMeans();
        long start = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            kMeans.run(points);
        }
        long time = (System.currentTimeMillis() - start) / iterations;
        System.out.println(MessageFormat.format("Made {0} iterations with an average of" +
                " {1} milliseconds", iterations, time));

    }

    public static void main(String[] args) throws Exception {
        new Main().main();
    }
}
