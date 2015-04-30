package us.arleighdickerson.cryptochallenge

object Challenge9 {
  def main(args: Array[String]): Unit = {
    println(new String("Yellow submarine".getBytes.padPKCB(20)))
  }
}
