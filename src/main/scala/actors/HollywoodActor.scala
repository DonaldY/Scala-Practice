package actors

import akka.actor.{AbstractActor, Actor}

class HollywoodActor extends Actor {

  override def receive: Receive = {
    case message => println(s"$message - ${Thread.currentThread()}")
  }
}
