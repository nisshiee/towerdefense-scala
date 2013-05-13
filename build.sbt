name := "towerdefense-scala"

organization := "org.nisshiee"

version := "1.0.1"

scalaVersion := "2.10.1"

resolvers += "Towerdefense Repo" at "http://towerdefense-repo.herokuapp.com/"

libraryDependencies ++= Seq(
   "jp.ac.nagoya-u.itc.mase" % "tower-defense" % "1.3.0"
  // "org.scalaz" %% "scalaz-core" % "7.0.0-RC2"
  ,"org.specs2" %% "specs2" % "1.14" % "test"
  //,"org.typelevel" %% "scalaz-specs2" % "0.1.3" % "test"
  ,"org.scala-lang" % "scala-reflect" % "2.10.1" % "test"
  ,"org.mockito" % "mockito-all" % "1.9.5" % "test"
  ,"junit" % "junit" % "4.11" % "test"
  ,"org.pegdown" % "pegdown" % "1.2.1" % "test"
)

scalacOptions <++= scalaVersion.map { sv =>
  if (sv.startsWith("2.10")) {
    Seq(
      "-deprecation",
      "-language:dynamics",
      "-language:postfixOps",
      "-language:reflectiveCalls",
      "-language:implicitConversions",
      "-language:higherKinds",
      "-language:existentials",
      "-language:reflectiveCalls",
      "-language:experimental.macros",
      "-Xfatal-warnings"
    )
  } else {
    Seq("-deprecation")
  }
}

testOptions in (Test, test) += Tests.Argument("console", "html", "junitxml")

initialCommands := """
import org.nisshiee.towerdefensescala._
"""


// ========== for sonatype oss publish ==========

publishMavenStyle := true

publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <url>https://github.com/nisshiee/towerdefense-scala</url>
  <licenses>
    <license>
      <name>The MIT License (MIT)</name>
      <url>http://opensource.org/licenses/mit-license.php</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:nisshiee/towerdefense-scala.git</url>
    <connection>scm:git:git@github.com:nisshiee/towerdefense-scala.git</connection>
  </scm>
  <developers>
    <developer>
      <id>nisshiee</id>
      <name>Hirokazu Nishioka</name>
      <url>http://nisshiee.github.com/</url>
    </developer>
  </developers>)


// ========== for scaladoc ==========

// scalacOptions in (Compile, doc) <++= (baseDirectory in LocalProject("core")).map {

scalacOptions in (Compile, doc) <++= baseDirectory.map {
  bd => Seq("-sourcepath", bd.getAbsolutePath,
            "-doc-source-url", "https://github.com/nisshiee/towerdefense-scala/blob/master€{FILE_PATH}.scala",
            "-implicits", "-diagrams")
}

