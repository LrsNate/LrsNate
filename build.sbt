name := """nate"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  "com.newrelic.agent.java" % "newrelic-agent" % "3.11.0",
  cache,
  ws
)
