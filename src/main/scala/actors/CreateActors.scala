package actors

import akka.actor.{ActorSystem, Props}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object CreateActors extends App {

  val system = ActorSystem("sample")

  val depp = system.actorOf(Props[HollywoodActor])
  val hanks = system.actorOf(Props[HollywoodActor])

  depp ! "Wonka"
  hanks ! "Gump"

  depp ! "Sparrow"
  hanks ! "Phillips"

  println(s"Calling from ${Thread.currentThread()}")

  val terminateFuture = system.terminate()
  Await.ready(terminateFuture, Duration.Inf)

}
