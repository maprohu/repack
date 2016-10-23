package repack.modules

import maven.modules.builder._
import maven.modules.utils.MavenCentralModule


/**
  * Created by martonpapp on 29/08/16.
  */
object RepackModules extends MavenCentralModule(
  "repack-modules",
  "repack-modules",
  "1.0.0-SNAPSHOT"
) {

  implicit val Root = RootModuleContainer("repack")

//  object DBus extends ScalaModule(
//    "dbus",
//    mvn.`libmatthew-debug-java:debug-disable:jar:1.1`,
//    mvn.`libunix-java:unix:jar:0.5`,
//    mvn.`libmatthew-debug-java:hexdump:jar:0.2`
//  )

}



object MavenModulesBuilder extends MavenCentralModule(
  "maven-modules",
  "maven-modules-builder",
  "1.0.0-SNAPSHOT"
)
