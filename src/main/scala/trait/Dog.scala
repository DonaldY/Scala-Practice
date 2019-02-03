package `trait`

class Dog(val name: String) extends Animal with Friend {

  // 选择性重写方法
  override def listen(): Unit = println(s"$name's listening quietly")
}
