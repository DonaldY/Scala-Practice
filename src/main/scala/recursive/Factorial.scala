package recursive

object Factorial extends App {

  // 是尾递归， 但不能尾调用优化（TCO）
  def factorial(number: Int): BigInt = {
    if (number == 0)
      1
    else
      number * factorial(number - 1)
  }

  // 确保尾调用优化
  // 如果不是尾调用会编译异常，编辑器也会报错
  @scala.annotation.tailrec
  def factorial(fact: BigInt, number: Int): BigInt = {
    if (number == 0)
      fact
    else
      factorial(fact * number, number - 1)
  }

  println(factorial(1, 10000))
}
