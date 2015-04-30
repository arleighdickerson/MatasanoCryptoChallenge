package io.arleigh.cryptochallenge

import scala.io.Source
import Codecs._
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
/**
 * @author Arleigh Dickerson
 * 
 * http://cryptopals.com/sets/1/challenges/7/
 */
object Challenge7 {
  val message = Base64.decode(Source
    .fromURL("http://cryptopals.com/static/challenge-data/7.txt")
    .getLines
    .foldLeft("")(_ + _))

  val key = "YELLOW SUBMARINE" getBytes

  def main(args: Array[String]): Unit = {
    println(new String(cbcDecrypt(key, message)))
  }

  def cbcDecrypt(key: Array[Byte], message: Array[Byte]) = {
    val cipher = Cipher.getInstance("AES/ECB/NoPadding", "SunJCE")
    cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"))
    cipher.doFinal(message)
  }
}
