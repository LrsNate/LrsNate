name := """nate"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.3.0-2",
  "org.webjars" % "jquery" % "2.1.3",
  "org.webjars" % "bootstrap" % "3.3.1",
  "org.webjars" % "angularjs" % "1.3.8",
  "org.webjars" % "angular-ui-router" % "0.2.13",
  "org.webjars" % "fastclick" % "1.0.3",
  "org.webjars" % "angular-ui-bootstrap" % "0.12.0",
  cache,
  ws
)
