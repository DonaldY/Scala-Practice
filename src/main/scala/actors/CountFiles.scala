package actors

import akka.actor.{ActorSystem, Props}

object CountFiles extends App {
  val system = ActorSystem("sample")

  val filesCounter = system.actorOf(Props[FilesCounter])

  filesCounter ! args(0)
}
