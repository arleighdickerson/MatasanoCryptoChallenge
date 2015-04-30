package us.arleighdickerson.cryptochallenge
import Codecs.Hex._

/**
 * @author Arleigh Dickerson
 * 
 * http://cryptopals.com/sets/1/challenges/2/
 */
object Challenge2 {
  def apply(buffer0: String, buffer1: String) =
    encode(decode(buffer0) xor decode(buffer1))

  def main(args: Array[String]) = {
    val message = "1c0111001f010100061a024b53535009181c"
    val against = "686974207468652062756c6c277320657965"
    val expect = "746865206b696420646f6e277420706c6179"

    println("Expected: '" + expect + "'")
    println("Actual:   '" + this(message, against) + "'")
  }
}
