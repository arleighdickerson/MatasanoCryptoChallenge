package set1

import scala.util.Try

trait Codec {
  def encode(bytes: Array[Byte]): String
  def decode(str: String): Array[Byte]

  def apply(bytes: Array[Byte]) = encode(bytes)
  def unapply(str: String) = Try(decode(str)).toOption
}
object Codecs {
  object Hex extends Codec {
    import org.apache.commons.codec.binary.Hex.{ decodeHex, encodeHexString }
    val digits = Array[Char]('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')
    def encode(bytes: Array[Byte]): String = encodeHexString(bytes)
    def decode(str: String): Array[Byte] = decodeHex(str.toCharArray)
  }
  object Base64 extends Codec {
    import org.apache.commons.codec.binary.Base64.{ decodeBase64, encodeBase64String }
    val digits = Array[Char](
      'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
      'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
      'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
      'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/')
    def encode(bytes: Array[Byte]): String = encodeBase64String(bytes)
    def decode(str: String): Array[Byte] = decodeBase64(str)
  }
}
