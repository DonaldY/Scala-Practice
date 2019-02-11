package exception

object ExceptionHanding extends App {

  for (amount <- List(100.0, 0.009, -2.0, 1000001.0))
    try {
      print(s"Amount: $$$amount")
      println(s"Tax: $$${Tax.taxFor(amount)}")
    } catch {
      case ex: IllegalArgumentException => println(ex.getMessage)
      case ex: RuntimeException =>
        // 如果需要一段代码来处理异常
        println(s"Don't bother reporting... ${ex.getMessage}")
    }

}
