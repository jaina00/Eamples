name := "TestApp"

version := "1.0"

scalaVersion := "2.11.8"


libraryDependencies ++= {
  Seq(
    "com.typesafe.akka" %% "akka-actor" % "2.4.1",
    "joda-time" % "joda-time" % "2.9.3",
    "com.typesafe.akka" %% "akka-stream" % "2.4.4",
    "org.json4s" %% "json4s-jackson" % "3.2.11",
    "org.scalatest" % "scalatest_2.11" % "3.0.1" % "test",
    "org.scala-lang" % "scala-reflect" % scalaVersion.value
  )
}