package us.arleighdickerson.cryptochallenge

import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.SecretKeySpec
import scala.io.Source
import Challenge7.cbcDecrypt
import Codecs.Base64

/**
 * @author Arleigh Dickerson
 *
 * http://cryptopals.com/sets/2/challenges/10/
 * 
 * still working on this one...
 */
object Challenge10 {
  val message =
    Base64 decode (Source.fromURL("http://cryptopals.com/static/challenge-data/10.txt")
      .getLines
      .foldLeft("")(_ + _))

  val plaintext = "bla" * 48 getBytes

  val key = "YELLOW SUBMARINE" getBytes

  val iv: Array[Byte] = Array.fill(4)(0x00 toByte)

  def cbcDecrypt(key: Array[Byte]) = {
  }

  def main(args: Array[String]): Unit = {
    val enc = aesEnc(Cipher.ENCRYPT_MODE)_
    val encrypted = {
      var cipherblock = iv
      (for (text <- plaintext.grouped(16)) yield {
        val result = enc(cipherblock xor text padPKCB 16)
        cipherblock = result
        result
      }).flatten.toArray
    }
    println(new String(encrypted))
    val dec = aesEnc(Cipher.DECRYPT_MODE)_
    val decrypted = {
      var cipherblock = iv
      (for (text <- encrypted.grouped(16)) yield {
        val result = dec(cipherblock xor text padPKCB 16)
        cipherblock = result
        result
      }).flatten.toArray
    }
  }

  def aesEnc(mode: Int)(message: Array[Byte]) = {
    val cipher = Cipher.getInstance("AES/ECB/NoPadding", "SunJCE")
    cipher.init(mode, SecretKeyFactory
      .getInstance("AES")
      .generateSecret(new SecretKeySpec(key, "AES")))
    cipher.doFinal(message)
  }
}
