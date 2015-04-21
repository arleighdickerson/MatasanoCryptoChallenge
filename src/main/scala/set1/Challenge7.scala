package set1
import scala.io.Source
import Codecs._
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
object AESinECBMode {
  val message = Base64.decode(Source
    .fromURL("http://cryptopals.com/static/challenge-data/7.txt")
    .getLines
    .foldLeft("")(_ + _))

  val key = {
    val text = "YELLOW SUBMARINE"
    new SecretKeySpec(text.getBytes, "AES")
  }
  val cipher = Cipher.getInstance("AES/ECB/NoPadding", "SunJCE")
  def main(args: Array[String]): Unit = {
    cipher.init(Cipher.DECRYPT_MODE, key)
    println(new String(cipher.doFinal(message)))
  }
}