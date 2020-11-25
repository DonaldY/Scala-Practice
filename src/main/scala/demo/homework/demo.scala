package demo.homework

import java.util.Date
import java.util.concurrent.{Executors, TimeUnit}

/**
  * @author donald
  * @date 2020/11/25
  */
object demo {


  def main(args: Array[String]): Unit = {
    val hello = new Thread(new Runnable {
      def run() {
        println("hello world")
      }
    })
  }

  /*val workerService = Executors.newSingleThreadScheduledExecutor()
  workerService.scheduleAtFixedRate(heartbeatTask, 3, 1, TimeUnit.SECONDS)*/

  //Thread.sleep(20000)
}
