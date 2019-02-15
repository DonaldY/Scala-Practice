package actors

import akka.actor.{AbstractActor, Actor}

class HollywoodActor extends Actor {

  override def receive: Receive = {
    case message => println(s"playing the role of $message")
  }
}
