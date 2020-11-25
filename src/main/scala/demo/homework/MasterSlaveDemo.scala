package demo.homework

import java.util.Date
import java.util.concurrent.{Executors, TimeUnit}

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

/**
  * @author donald
  * @date 2020/11/25
  */

case class HeartBeatMsg(user: Master, msg: String)
case class StopMsg()

object MasterSlaveDemo {

  def main(args: Array[String]): Unit = {

    val MasterFactory = ActorSystem("MasterFactory")
    val masterRef = MasterFactory.actorOf(Props[MasterActor], "master")

    val master = new Master()
    master.init()

    val heartbeatTask = new Runnable {

      override def run(): Unit = {

        masterRef ! HeartBeatMsg(master, "workerA")
      }
    }

    val workerService = Executors.newSingleThreadScheduledExecutor()
    workerService.scheduleAtFixedRate(heartbeatTask, 1, 3, TimeUnit.SECONDS)

    Thread.sleep(10000)

    workerService.shutdownNow()

    Thread.sleep(10000)

    master.stop(masterRef)
  }
}

class MasterActor extends Actor {

  override def receive: Receive = {

    case HeartBeatMsg(master, workerName) => master.updateWorker(workerName)
    case StopMsg() =>
      context.stop(self)
      context.system.terminate()
  }
}

class Master {

  private val service = Executors.newSingleThreadScheduledExecutor()

  var workerMap: Map[String, Date] = Map()

  def stop(actorRef: ActorRef): Unit = {

    actorRef ! StopMsg()

    service.shutdownNow()
  }

  def init(): Unit = {

    val cleanTask = new Runnable {
      override def run(): Unit = {

        val currDate = new Date()

        for ((k, v) <- workerMap) {

          val second = (currDate.getTime - v.getTime) / 1000

          if (second > 5) {

            println(s"master 移除 ： $k")
            workerMap -= k
          }
        }
      }
    }

    this.service.scheduleAtFixedRate(cleanTask, 1, 3, TimeUnit.SECONDS)
  }

  def updateWorker(workerName: String): Unit = {

    println(s"master 接收 $workerName 的 heartbeat ")

    workerMap += (workerName -> new Date())
  }
}