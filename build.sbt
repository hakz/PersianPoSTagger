name := """PersianPoSTagger"""

version := "1.0"

scalaVersion := "2.10.2"

resolvers += "OpenNLP Maven Repository" at "http://opennlp.sourceforge.net/maven2"

libraryDependencies += "org.scalatest" %% "scalatest" % "1.9.1" % "test"

libraryDependencies += "org.apache.opennlp" % "opennlp-maxent" % "3.0.3"

libraryDependencies += "org.apache.opennlp" % "opennlp-tools" % "1.5.3"

libraryDependencies += "commons-io" % "commons-io" % "2.3"
