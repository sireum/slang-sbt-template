scalaVersion := "2.13.5" // see https://github.com/sireum/kekinian/blob/master/versions.properties#L2
resolvers ++= Seq(Resolver.sonatypeRepo("public"), "jitpack" at "https://jitpack.io")
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.5" % "test" // see https://github.com/sireum/kekinian/blob/master/versions.properties#L4
libraryDependencies += "org.sireum.kekinian" %% "library" % ghLatestTag("sireum", "kekinian")
addCompilerPlugin("org.sireum" %% "scalac-plugin" % ghLatestTag("sireum", "scalac-plugin"))

def ghLatestTag(owner: String, repo: String): String = {
  import ammonite.ops._
  val out = %%("git", "ls-remote", "--tags", "--refs", "--sort=v:refname", s"https://github.com/$owner/$repo.git")(pwd).out
  val lines = for (line <- out.lines if line.contains("refs/tags/4.")) yield line
  val last = lines(lines.length - 1)
  last.substring(last.indexOf("refs/tags/") + "refs/tags/".length).trim
}
