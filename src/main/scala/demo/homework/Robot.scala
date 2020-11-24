package demo.homework

import scala.io.StdIn
import scala.util.Random

/**
  * @author donald
  * @date 2020/11/23
  *
  * 人机猜拳
  *
  * 1. 选取对战角色
  * 2. 开始对战，用户出拳，与对手进行比较，提示胜负信息
  * 3. 猜拳结束算分，平局都加一分，获胜加二分，失败不加分
  * 4. 循环对战，当输入 “n” 时，终止对战，并显示对战结果
  * 5. 游戏结束后显示得分
  */
object Robot {

  val actorMap: Map[Int, String] = Map((1, "刘备"), (2, "关羽"), (3, "张飞"))
  val whatMap: Map[Int, String] = Map((1, "剪刀"), (2, "石头"), (3, "布"))

  var round, win, lose = 0

  def visitorWhat(): Int = {

    val what = StdIn.readInt()
    val whatName = whatMap(what)

    if (whatName == null) {
      println("输入不合规范，默认出布!")
      return 1
    }
    println("你出拳: " + whatName)

    what
  }

  def robotWhat(actorName: String): Int = {

    val what = Random.nextInt(3) + 1
    val whatName = whatMap(what)

    println(actorName + "出拳！")

    println(actorName + "出拳: " + whatName)

    what
  }

  def executeWhat(what: Int, randomWhat: Int): Unit = {

    round += 1
    if (what == randomWhat) {
      println("结果: 和局!  下次继续努力!")
    } else if (what == 1) {
      if (randomWhat == 2) {
        win += 1
        println("结果: 你输了!")
      } else {
        lose += 1
        println("结果: 恭喜,你赢啦!")
      }
    } else if (what == 2) {
      if (randomWhat == 1) {
        lose += 1
        println("结果: 恭喜,你赢啦!")
      } else {
        win += 1
        println("结果: 你输了!")
      }
    } else {
      if (randomWhat == 1) {
        win += 1
        println("结果: 你输了!")
      } else {
        lose += 1
        println("结果: 恭喜,你赢啦!")
      }
    }
  }

  def roundWhatOnce(actorName: String): Unit = {

    println("请出拳! 1.剪刀 2.石头 3.布")

    val what = visitorWhat()

    val randomWhat = robotWhat(actorName)

    executeWhat(what, randomWhat)
  }

  def main(args: Array[String]): Unit = {

    println("-------------欢迎进入游戏世界-------------")
    println("***************************************")
    println("****************猜拳，开始***************")
    println("***************************************")
    println("请选择对战角色：(1.刘备 2.关羽 3.张飞)")

    val actorNum = StdIn.readInt()

    val currActorName = actorMap.getOrElse(actorNum, "未知人").toString

    println("你选择与" + currActorName + "对战")

    println("要开始么？ y/n")

    if (StdIn.readLine() != "y") System.exit(0)

    roundWhatOnce(currActorName)

    println("是否开始下一轮(y/n)")

    while (StdIn.readLine() == "y") {

      roundWhatOnce(currActorName)

      println("是否开始下一轮(y/n)")
    }

    println("退出游戏！")
    println("---------------------------------------")
    println(s"$currActorName VS 游客")
    println(s"对战次数 $round 次")
    println()
    println()
    val peace = round - win - lose
    val visitorSum = 2 * lose + peace
    val robotSum = 2 * win + peace
    println("姓名    得分    胜局    和局    负局")
    println("游客     " + visitorSum + "      " + lose + "      " + peace + "      " + win)
    println(s"$currActorName     " + robotSum + "      " + win + "      " + peace + "      " + lose)
  }
}
