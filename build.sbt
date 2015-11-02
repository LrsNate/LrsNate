name := """nate"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.reactivemongo" %% "play2-reactivemongo" % "0.11.4.play23",
  "org.webjars" %% "webjars-play" % "2.3.0-3",
  "org.webjars" % "bootstrap" % "3.3.5",
  "org.webjars" % "angularjs" % "1.3.8",
  "org.webjars" % "angular-ui-router" % "0.2.13",
  "org.webjars" % "fastclick" % "1.0.3",
  "org.webjars" % "angular-ui-bootstrap" % "0.12.0",
  "org.scalatestplus" %% "play" % "1.2.0" % "test",
  cache,
  ws
)
