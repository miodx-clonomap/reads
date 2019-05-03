name          := "reads"
organization  := "com.miodx.clonomap"
version       := "0.3.0"
description   := ""
scalaVersion  := "2.11.11"

bucketSuffix  := "era7.com"

libraryDependencies ++= Seq(
  "com.miodx.common" %% "fastarious" % "0.11.0",
  "org.scalatest"    %% "scalatest"  % "3.0.5" % Test
)

// // For resolving dependency versions conflicts:
// dependencyOverrides ++= Set()

// // If you need to deploy this project as a Statika bundle:
// generateStatikaMetadataIn(Compile)

// // This includes tests sources in the assembled fat-jar:
// fullClasspath in assembly := (fullClasspath in Test).value

// // This turns on fat-jar publishing during release process:
// publishFatArtifact in Release := true

// shows time for each test:
testOptions in Test += Tests.Argument("-oD")
// disables parallel exec
parallelExecution in Test := false
