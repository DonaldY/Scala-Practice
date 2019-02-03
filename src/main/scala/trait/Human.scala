package `trait`

class Human(val name: String) extends Friend

class Man(override val name: String) extends Human(name)

class Woman(override val name: String) extends Human(name)