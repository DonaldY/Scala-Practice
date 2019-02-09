package collection

class Cow {
  def ^(moon: Moon): Unit = println("Cow jumped over the moon")
}

class Moon {
  def ^:(cow: Cow): Unit = println("This cow jumped over the moon too")
}

class Sample {

  def unary_+(): Unit = println("Called unary +")
  def unary_-(): Unit = println("Called unary -")
  def unary_!(): Unit = println("Called unary !")
  def unary_~(): Unit = println("called unary ~")
}

object Colon extends App {
  val cow = new Cow
  val moon = new Moon

  cow ^ moon
  cow ^: moon

  val sample = new Sample
  +sample
  -sample
  !sample
  ~sample
}
