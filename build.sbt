name := "TestApp"

version := "1.0"

scalaVersion := "2.11.8"

//scalacOptions ++= Seq("-unchecked", "-feature", "-Xfatal-warnings")

libraryDependencies ++= {
  Seq(
    "com.typesafe.akka" %% "akka-actor" % "2.4.1",
    "joda-time" % "joda-time" % "2.9.3",
    "com.typesafe.akka" %% "akka-stream" % "2.4.4",
    "org.json4s" %% "json4s-jackson" % "3.2.11",
    "org.scalatest" % "scalatest_2.11" % "3.0.1" % "test",
    "org.scala-lang" % "scala-reflect" % scalaVersion.value,
    "org.typelevel" %% "cats" % "0.7.2",
    "com.typesafe.akka" %% "akka-cluster" % "2.4.17",
    "com.typesafe.akka" %% "akka-persistence" % "2.4.0",
    "org.iq80.leveldb" % "leveldb" % "0.7"

  )
}