package repack.modules

import java.io.File

import mvnmod.builder.MavenTools

/**
  * Created by martonpapp on 02/10/16.
  */
object RunInstallRepackJars {

  def main(args: Array[String]): Unit = {
    val ids = Seq(
      run(
        "hexdump",
        "0.2",
        "libmatthew-debug-java"
      ),
      run(
        "debug-enable",
        "1.1",
        "libmatthew-debug-java"
      ),
      run(
        "debug-disable",
        "1.1",
        "libmatthew-debug-java"
      ),
      run(
        "unix",
        "0.5",
        "libunix-java"
      ),
      run(
        "dbus",
        "2.8",
        "libdbus-java"
      ),
      run(
        "dbus",
        "2.8",
        "libdbus-java",
        Some("sources")
      ),
      run(
        "dbus-bin",
        "2.8",
        "libdbus-java"
      ),
      run(
        "dbus-bin",
        "2.8",
        "libdbus-java",
        Some("sources")
      ),
      run(
        "jsyn",
        "16.7.6",
        "com.jsyn"
      )
    )

    print(
      ids
        .map(i => s""""${i}"""")
        .mkString(",\n")
    )
  }


  def run(
    name: String,
    version: String,
    groupId: String,
    classifier: Option[String] = None
  ) = {

    MavenTools.runMaven(
      MavenTools.pom(
        <build>
          <plugins>
            <plugin>
              <groupId>org.apache.maven.plugins</groupId>
              <artifactId>maven-install-plugin</artifactId>
              <version>2.5.2</version>
              <executions>
                <execution>
                  <id>install</id>
                  <phase>package</phase>
                  <goals>
                    <goal>install-file</goal>
                  </goals>
                  <configuration>
                    <file>{new File(s"../repack/jars/${name}-${version}${classifier.map(c => s"-$c").getOrElse("")}.jar").getAbsolutePath}</file>
                    <groupId>{groupId}</groupId>
                    <artifactId>{name}</artifactId>
                    <version>{version}</version>
                    {classifier.map(c => <classifier>{c}</classifier>).getOrElse()}
                    <packaging>jar</packaging>
                  </configuration>
                </execution>
              </executions>
            </plugin>
          </plugins>
        </build>
      ),
      "package"
    ){ _ => }

    s"${groupId}:${name}:jar:${version}"
  }

}
