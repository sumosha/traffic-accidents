name := "traffic-accidents"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"

libraryDependencies += "org.apache.spark" % "spark-core_2.11" % "2.2.0" % "provided"
libraryDependencies += "org.apache.spark" % "spark-sql_2.11" % "2.2.0" % "provided"
libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.5"
libraryDependencies += "com.google.guava" % "guava" % "12.0.1"
libraryDependencies += "com.github.scopt" % "scopt_2.11" % "3.7.0"
    