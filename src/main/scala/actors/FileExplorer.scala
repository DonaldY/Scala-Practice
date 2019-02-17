package actors

import java.io._

import akka.actor._

class FileExplorer extends Actor {
  override def receive: Receive = {
    case dirName: String =>
      val file = new File(dirName)
      val children = file.listFiles()
      var filesCount = 0

      if (children != null) {
        children.filter { _.isDirectory }
          .foreach { sender ! _.getAbsoluteFile}
        filesCount = children.count { !_.isDirectory }
      }

      sender ! filesCount

  }
}
