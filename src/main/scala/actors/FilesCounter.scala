package actors

import akka.actor.{Actor, ActorRef, Props}
import akka.routing.RoundRobinPool

class FilesCounter extends Actor {

  val start: Long = System.nanoTime()
  var filesCount = 0L
  var pending = 0

  val fileExplorer: ActorRef =
    context.actorOf(RoundRobinPool(100).props(Props[FileExplorer]))

  def receive: Receive = {
    case dirName: String =>
      pending = pending + 1
      fileExplorer ! dirName

    case count: Int =>
      filesCount = filesCount + count
      pending = pending - 1

      if (pending == 0) {
        val end = System.nanoTime()
        println(s"Files count: $filesCount")
        println(s"Time taken: ${(end - start) / 1.0e9} seconds")
        context.system.terminate()
      }

  }
}
