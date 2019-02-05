package `trait`

abstract class Writer {
  def writeMessage(message: String): Unit
}


trait UpperCaseWriter extends Writer {
  abstract override def writeMessage(message: String): Unit =
    super.writeMessage(message.toUpperCase)
}

trait ProfanityFilteredWriter extends Writer {
  abstract override def writeMessage(message: String): Unit =
    super.writeMessage(message.replace("stupid", "s-----"))
}

class StringWriterDelegate extends Writer {
  val writer = new java.io.StringWriter

  override def writeMessage(message: String): Unit = writer.write(message)

  override def toString: String = writer.toString
}

