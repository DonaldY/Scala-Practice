package recursive

object Mad extends App {

  def mad(parameter: Int): Int = {
    if (parameter == 0)
      throw new RuntimeException("Error")
    else
      1 * mad(parameter - 1)
  }

  def mad2(parameter: Int): Int = {
    if (parameter == 0)
      throw new RuntimeException("Error")
    else
      mad2(parameter - 1)
  }

  // 执行 6 次
  // mad(5)

  // 执行 1 次
  mad2(5)
}
