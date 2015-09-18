name := "kmeans"

organization := "unicredit"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.7"

scalacOptions ++= Seq("-deprecation", "-feature", "-language:postfixOps", "-language:implicitConversions")

resolvers ++= Seq()

libraryDependencies ++= Seq(
  "org.json4s" %% "json4s-jackson" % "3.2.11"
)