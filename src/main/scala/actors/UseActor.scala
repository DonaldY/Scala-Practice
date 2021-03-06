package actors

import akka.actor._
import akka.util.Timeout
import akka.pattern.ask
import scala.concurrent.Await
import scala.concurrent.duration._

object UseActor extends App {

  val system = ActorSystem("sample")

  val depp = system.actorOf(Props[HollywoodActor])
  val hanks = system.actorOf(Props[HollywoodActor])

  depp ! Play("Wonka")
  hanks ! Play("Gump")

  depp ! Play("Wonka")
  depp ! Play("Sparrow")

  println("Sent roles to play")

  implicit val timeout: Timeout = Timeout(2.seconds)
  val wonkaFuture = depp ? ReportCount("Wonka")
  val sparrowFuture = depp ? ReportCount("Sparrow")
  val gumpFuture = hanks ? ReportCount("Gump")

  val wonkaCount = Await.result(wonkaFuture, timeout.duration)
  val sparrowCount = Await.result(sparrowFuture, timeout.duration)
  val gumpCount = Await.result(gumpFuture, timeout.duration)

  println(s"Depp played Wonka $wonkaCount time(s)")
  println(s"Depp played Sparrow $sparrowCount time(s)")
  println(s"Hanks played Gump $gumpCount time(s)")

  val terminateFuture = system.terminate()
  Await.ready(terminateFuture, Duration.Inf)

}
