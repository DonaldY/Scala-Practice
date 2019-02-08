package collection

object mutable extends App {

  val feeds = List("blog.toolshed.com", "pragdave.me", "blog.agiledeveloper.com")

  println(s"First feed: ${feeds.head}")
  println(s"Second feed: ${feeds(1)}")

  val prefixedList = "forums.pragprog.com/forums/87" :: feeds
  println(s"First Feed In Prefixed: ${prefixedList.head}")

  val feedsWithForums = feeds ::: List(
    "forums.pragprog.com/forums/87",
    "forums.pragprog.com/forums/246"
  )
  println(s"First feed in feeds with forum: ${feedsWithForums.head}")
  println(s"Last feed in feeds with forum: ${feedsWithForums.last}")

  println(s"Feeds with blog: ${feeds.filter(_ contains "blog").mkString(", ")}")
  println(s"All feeds have com: ${feeds.forall(_ contains "com")}")
  println(s"Any feeds has dave: ${feeds.forall(_ contains "dave")}")
  println(s"Any feeds has bill: ${feeds.exists(_ contains "bill")}")
}
