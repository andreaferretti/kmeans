name := "kmeans-java"

organization := "unicredit"

version := "0.1-SNAPSHOT"

resolvers ++= Seq()

mainClass in (Compile, run) := Some("Entry")

libraryDependencies ++= Seq(
	"com.fasterxml.jackson.core" % "jackson-core" % "2.5.1"
)
