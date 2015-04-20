package set1
import Codecs.Hex.{ encode, decode }

object FixedXOR {
  def apply(buffer0: String, buffer1: String) = encode(
    decode(buffer0)
      .zip(decode(buffer1))
      .map((t) => (t._1 ^ t._2).toByte))
}