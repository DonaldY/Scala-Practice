package demo.homework

import akka.actor.{Actor, ActorSystem, Props}

/**
  * @author donald
  * @date 2020/11/25
  */
case class AMessage()
case class BMessage()

object HelloActor {

  var aCnt, bCnt = 0

  private val MyFactory = ActorSystem("myFactory")

  private val aActorRef = MyFactory.actorOf(Props[ActorDemo], "AActor")
  private val bActorRef = MyFactory.actorOf(Props[ActorDemo], "BActor")

  def main(args: Array[String]): Unit = {

    println("比赛开始: ")
    aActorRef ! AMessage()
  }

  class ActorDemo extends Actor {

    def stop(cnt: Int): Unit = {

      if (cnt >= 10) {
        println("告辞！")

        context.stop(self)
        context.system.terminate()
      }
    }

    // 接收消息并处理
    override def receive: Receive = {
      case AMessage() => {
        aCnt += 1
        println(s"(黄飞鸿) : 厉害！ 佛山无影脚 第 $aCnt 脚")
        bActorRef ! BMessage()
        stop(aCnt)
      }
      case BMessage() => {
        bCnt += 1
        println(s"(乔峰) : 挺猛！ 降龙十八掌 第 $bCnt 掌")
        aActorRef ! AMessage()
        stop(bCnt)
      }
    }
  }
}