name := """nate"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

routesGenerator := InjectedRoutesGenerator

libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.4.0",
  "org.webjars" % "bootstrap" % "3.3.5",
  "org.webjars" % "angularjs" % "1.4.6",
  "org.webjars" % "fastclick" % "1.0.3",
  "org.webjars" % "angular-ui-bootstrap" % "0.12.0",
  "org.reactivemongo" %% "play2-reactivemongo" % "0.11.10",
  cache,
  ws
)
