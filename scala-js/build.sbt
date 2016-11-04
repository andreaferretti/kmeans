 enablePlugins(ScalaJSPlugin)

name := "kmeans"

organization := "unicredit"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.8"

scalacOptions ++= Seq("-deprecation", "-feature", "-language:postfixOps", "-language:implicitConversions")

scalaJSOutputWrapper := ("global.require = require;", "")

scalaJSUseRhino in Global := false

persistLauncher in Compile := true