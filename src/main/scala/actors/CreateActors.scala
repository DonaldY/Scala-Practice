package actors

import akka.actor.{ActorSystem, Props}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object CreateActors extends App {

  val system = ActorSystem("sample")

  val depp = system.actorOf(Props[HollywoodActor])

  depp ! "Wonka"

  depp.tell("Wooo", depp)

  val terminateFuture = system.terminate()
  Await.ready(terminateFuture, Duration.Inf)

}
