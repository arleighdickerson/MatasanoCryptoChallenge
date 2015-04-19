package set1
import org.apache.commons.codec.binary.{ Hex, Base64 }

object FixedXOR {
  def apply(buffer0: String, buffer1: String) = {
    val bytes0 = Hex.decodeHex(buffer0.toCharArray)
    val bytes1 = Hex.decodeHex(buffer1.toCharArray)
    val xord = bytes0.zip(bytes1).map((t: (Byte, Byte)) => (t._1 ^ t._2).toByte)
    Hex.encodeHexString(xord)
  }
}