name := """play-java-app"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  evolutions,
  "org.webjars" % "bootstrap" % "3.3.5"
)

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

fork in run := false

// autoreload は OSレベルのファイル変更検知を受け取るため、OSやファイルシステムによっては正常に動作しない場合がある。
// その場合以下のオプションを使うことで原始的なポーリングを行う事ができる。
//PlayKeys.fileWatchService  := play.runsupport.FileWatchService.sbt(500)
