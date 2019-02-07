package collection

object UsingSet extends App {

  val colors1 = Set("Blue", "Green", "Red")
  println(s"colors1: $colors1")

  val colors2 = colors1 + "Black"
  println(s"colors2: $colors2")
  println(s"colors1: $colors1")

  val feeds1 = Set("blog.toolshed.com", "pragdave.me", "blog.agiledeveloper.com")
  val feeds2 = Set("blog.toolshed.com", "martifowler.com/bliki")

  val blogFeeds = feeds1 filter (_ contains "blog")
  println(s"blog feeds: ${blogFeeds.mkString(",")}")

  val mergedFeeds = feeds1 ++ feeds2
  println(s"# of merged feeds: ${mergedFeeds.size}")

  val commonFeeds = feeds2 & feeds1
  println(s"common feeds: ${commonFeeds.mkString(", ")}")

  val urls = feeds1 map ("http://" + _)
  println(s"One url: ${urls.head}")

  println("Refresh Feeds:")
  feeds1 foreach { feed => println(s" Refreshing $feed...")}
}
