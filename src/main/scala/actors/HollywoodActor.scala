package actors

import akka.actor.{AbstractActor, Actor}
import scala.collection._

case class Play(role: String)
case class ReportCount(role: String)

class HollywoodActor extends Actor {

  override def receive: Receive = {
    case Play(role) =>
      val currentCount = messagesCount.getOrElse(role, 0)
      messagesCount.update(role, currentCount + 1)
      println(s"Playing $role")

    case ReportCount(role) =>
      sender ! messagesCount.getOrElse(role, 0)
  }

  val messagesCount: mutable.Map[String, Int] = mutable.Map()


}
