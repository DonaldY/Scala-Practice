package demo.homework

import scala.beans.BeanProperty

/**
  * @author donald
  * @date 2020/11/25
  */
object UserLocationStat extends App {

  val userList: List[UserLocation] = List(new UserLocation("UserA", "LocationA", 8, 60),
                                          new UserLocation("UserA", "LocationA", 9, 60),
                                          new UserLocation("UserB", "LocationB", 10, 60),
                                          new UserLocation("UserB", "LocationB", 11, 60))

  var userMap: Map[UserLocation, UserLocation] = Map()

  for (userLocation <- userList) {

    if (userMap.contains(userLocation)) {

      val tmp = userMap(userLocation)

      if (tmp.startTime > userLocation.getStartTime)
        tmp.startTime = userLocation.getStartTime

      tmp.duration += userLocation.duration
    } else {

      userMap += (userLocation -> userLocation)
    }
  }

  println(userMap.values)
}

class UserLocation(@BeanProperty val username: String,
                   @BeanProperty val location: String,
                   @BeanProperty var startTime: Int,
                   @BeanProperty var duration: Int) {

  override def hashCode(): Int = {
    this.username.hashCode + this.location.hashCode
  }

  override def equals(obj: Any): Boolean = {
    if (!obj.isInstanceOf[UserLocation]) return false
    else {
      val userLocation = obj.asInstanceOf[UserLocation]
      if (!userLocation.username.equals(this.username)) return false
      if (!userLocation.location.equals(this.location)) return false
    }
    true
  }

  override def toString: String = {
    "[username: " + this.username + " , location: " +
      this.location + " , startTime: " + this.startTime + " , duration: " + duration + " ]"
  }
}
