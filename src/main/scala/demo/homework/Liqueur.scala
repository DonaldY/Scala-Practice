package demo.homework

/**
  * @author donald
  * @date 2020/11/23
  *
  * 每瓶啤酒 2元，3个空酒瓶或者 5个瓶盖可换 1瓶啤酒。100元最多可喝多少瓶啤酒？（不允许借啤酒）
  */
object Liqueur {

  def main(args: Array[String]): Unit = {

    val BEER = 2
    var bottleCapNum : Int = 0 // 瓶盖
    var emptyWineBottleNum : Int = 0 // 空酒瓶
    var beerNum : Int = 100 / BEER
    var sumBeer : Int = 0 //

    while (beerNum != 0) {

      bottleCapNum += beerNum
      emptyWineBottleNum += beerNum
      sumBeer += beerNum
      beerNum = 0

      beerNum += bottleCapNum / 5
      bottleCapNum -= (bottleCapNum / 5) * 5
      beerNum += emptyWineBottleNum / 3
      emptyWineBottleNum -= (emptyWineBottleNum / 3) * 3
    }

    printf(s"$sumBeer")
  }
}
