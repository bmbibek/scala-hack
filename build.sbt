import play.Project._

name := """scala-hack-egit"""

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.2.2", 
  "org.webjars" % "bootstrap" % "2.3.1"
)

scalaVersion := "2.11.x"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies += "com.typesafe.akka" % "akka-actor_2.10" % "2.2-M1"
libraryDependencies += "com.google.code.gson" % "gson" % "1.7.1"


playScalaSettings



fork in run := true