name := "git-message-extractor-mockup"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++=Seq(
  "org.scalaj" %% "scalaj-http" % "1.1.6",
  "org.scalatest" % "scalatest_2.11" % "2.2.2" % "test",
  "net.liftweb" %% "lift-json" % "2.6+"
)
