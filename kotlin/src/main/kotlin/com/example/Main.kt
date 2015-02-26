package com.example

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File


fun main(args: Array<String>) {
    fun readPoints(path: String): List<Point> {
        val json = JsonFactory().createParser(File(path))
        val mapper = ObjectMapper()
        var typeref = object : TypeReference<List<List<Double>>>() {}
        return mapper.readValue<List<List<Double>>>(json, typeref).map{ Point(it[0], it[1]) };
    }

    val iterations = 100
    val points = readPoints("/tmp/points.json")
    val start = System.currentTimeMillis()
    for (i in 1..iterations) {
        KMeans.run(points)
    }
    val time = (System.currentTimeMillis() - start) / iterations

    println("Made $iterations iterations with an average of $time milliseconds")
}