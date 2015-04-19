package set1
import org.apache.commons.codec.binary.{ Hex, Base64 }

object BaseConversions {
  def hexToBase64(hex: Array[Char]) = Base64.encodeBase64String(Hex.decodeHex(hex))
}
