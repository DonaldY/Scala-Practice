package recursive

object Words extends App {

  def explore(count: Int, words: List[String]): Int =
    if (words.isEmpty)
      count
    else
      countPalindrome(count, words)

  def countPalindrome(count: Int, words: List[String]): Int = {
    val firstWord = words.head

    if (firstWord.reverse == firstWord)
      explore(count + 1, words.tail)
    else
      explore(count, words.tail)
  }

  def callExplore(text: String): Unit = println(explore(0, text.split(" ").toList))

  callExplore("dad mom and racecar")

  try {
    // val text = fromURL("https://en.wiki.pedia.org").mkString
    callExplore("dad mom and racecar")
  } catch {
    case ex: Throwable => println(ex)
  }

}
