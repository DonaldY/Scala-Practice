package recursive

object Factorial extends App {

  // 是尾递归， 但不能尾调用优化（TCO）
  def factorial(number: Int): BigInt = {
    if (number == 0)
      1
    else
      number * factorial(number - 1)
  }
}
