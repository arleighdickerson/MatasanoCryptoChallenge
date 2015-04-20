package set1
import Codecs.Hex._

object FixedXOR {
  def apply(buffer0: String, buffer1: String) = encode(
    decode(buffer0)
      .zip(decode(buffer1))
      .map((t) => (t._1 ^ t._2).toByte))

  def main(args: Array[String]) = {
    val message = "1c0111001f010100061a024b53535009181c"
    val against = "686974207468652062756c6c277320657965"
    val expect = "746865206b696420646f6e277420706c6179"

    println("Expected: '" + expect + "'")
    println("Actual:   '" + this(message, against) + "'")
  }
}