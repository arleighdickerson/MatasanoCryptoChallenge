package io.arleigh.cryptochallenge

import Codecs._
/**
 * @author Arleigh Dickerson
 * 
 * http://cryptopals.com/sets/1/challenges/1/
 */
object Challenge1 {
  def main(args: Array[String]) = {
    val message = "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d"
    val expected = "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t"
    val actual = Base64 encode (Hex decode message)

    println("Expected: '" + expected + "'")
    println("Actual:   '" + actual + "'")
  }
}