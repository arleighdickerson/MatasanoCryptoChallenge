package set1

import scala.util.Try

trait Codec {
  def encode(bytes: Array[Byte]): String
  def decode(str: String): Array[Byte]

  def apply(bytes: Array[Byte]) = encode(bytes)
  def unapply(str: String) = Try(decode(str)).toOption
}
object Codecs {
  implicit def string2bytes(str: String) = str.getBytes("US-ASCII")
  object Hex extends Codec {
    import org.apache.commons.codec.binary.Hex.{ decodeHex, encodeHexString }
    def encode(bytes: Array[Byte]): String = encodeHexString(bytes)
    def decode(str: String): Array[Byte] = decodeHex(str.toCharArray)
  }
  object Base64 extends Codec {
    import org.apache.commons.codec.binary.Base64.{ decodeBase64, encodeBase64String }
    def encode(bytes: Array[Byte]): String = encodeBase64String(bytes)
    def decode(str: String): Array[Byte] = decodeBase64(str)
  }
}
