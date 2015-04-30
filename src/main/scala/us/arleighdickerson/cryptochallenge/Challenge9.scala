package us.arleighdickerson.cryptochallenge

/**
 * @author Arleigh Dickerson
 *
 * http://cryptopals.com/sets/2/challenges/9/
 */
object Challenge9 {
  def main(args: Array[String]): Unit = {
    println(new String("Yellow submarine".getBytes.padPKCB(20)))
  }
}
