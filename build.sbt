scalaVersion := "2.13.3"
resolvers ++= Seq(Resolver.sonatypeRepo("public"), "jitpack" at "https://jitpack.io")
libraryDependencies += "org.sireum.kekinian" %% "library" % "4.20201109.deb4e5d" // or ghLatestCommit("sireum", "kekinian", "master")
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.2" % "test"
addCompilerPlugin("org.sireum" %% "scalac-plugin" % "4.20201007.2b4c4e9")

def ghLatestCommit(owner: String, repo: String, branch: String): String = {
  import ammonite.ops._
  val out = %%("git", "ls-remote", s"https://github.com/$owner/$repo.git")(pwd).out
  for (line <- out.lines if line.contains(s"refs/heads/$branch"))
    return line.substring(0, 10)
  throw new RuntimeException(s"Could not determine latest commit for https://github.com/$owner/$repo.git branch $branch!")
}