package com.example

import kotlin.io.readText
import java.io.File
import com.fasterxml.jackson.module.kotlin.*
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.core.JsonFactory

/**
 * Created by evacchi on 26/02/15.
 */


fun main(args: Array<String>) {
    fun readPoints(path: String): List<Point> {
        val json = JsonFactory().createParser(File(path))
        val mapper = ObjectMapper().registerKotlinModule()
        val jparser = mapper.readValues(json, javaClass<Point>())

        return jparser.readAll()
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