package collection

object UsingMap extends App {

  val feeds = Map(
    "Andy Hunt" -> "blog.toolshed.com",
    "Dave Thomas" -> "pragdave.me",
    "NFJS" -> "nofluffjuststuff.com/blog"
  )

  // D开头的map
  val filterNameStartWithD = feeds filterKeys (_ startsWith "D")
  println(s"# of Filtered: ${filterNameStartWithD.size}")


  val filterNameStartWithDAndPragprogInFeed = feeds filter {
    element => val (key, value) = element
     (key startsWith "D") && (value contains "pragdave")
  }

  print("# of feeds with auth name D* and pragdave in URL: ")
  println(filterNameStartWithDAndPragprogInFeed.size)


}
