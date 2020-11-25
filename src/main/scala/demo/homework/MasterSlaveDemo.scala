package demo.homework

import java.util.Date
import java.util.concurrent.{Executors, TimeUnit}

import akka.actor.{Actor, ActorSystem, Props}

/**
  * @author donald
  * @date 2020/11/25
  */

case class HeartBeatMsg(msg: String)

object MasterSlaveDemo {

  private val MasterFactory = ActorSystem("MasterFactory")

  private val masterRef = MasterFactory.actorOf(Props[Master], "master")


  def main(args: Array[String]): Unit = {

    //new Master("master")

    val heartbeatTask = new Runnable {

      override def run(): Unit = {

        masterRef ! HeartBeatMsg("workerA")
      }
    }

    val workerService = Executors.newSingleThreadScheduledExecutor()
    workerService.scheduleAtFixedRate(heartbeatTask, 3, 1, TimeUnit.SECONDS)


    Thread.sleep(20000)
  }

  class Master extends Actor{

    private val service = Executors.newSingleThreadScheduledExecutor()

    var workerMap: Map[String, Date] = Map()

    def this(name: String) {

      this()

      val cleanTask = new Runnable {
        override def run(): Unit = {

          val currDate = new Date()

          for ((k, v) <- workerMap) {

            val second = (currDate.getTime - v.getTime) / 1000

            if (second > 10) {

              println(s"master 移除 ： $k")
              workerMap -= k
            }
          }
        }
      }

      this.service.scheduleAtFixedRate(cleanTask, 5, 1, TimeUnit.SECONDS)
    }

    def updateWorker(workerName: String): Unit = {

      println(s"master 接收 heartbeat : $workerName")

      workerMap += (workerName -> new Date())
    }

    override def receive: Receive = {

      case HeartBeatMsg(workerName) => updateWorker(workerName)
    }
  }

}

