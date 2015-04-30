package us.arleighdickerson.cryptochallenge
import Codecs._

/**
 * @author Arleigh Dickerson
 * 
 * http://cryptopals.com/sets/1/challenges/3/
 */
object Challenge3 {
  def main(args: Array[String]) = {
    val message = "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736"
    val words = scala.io.Source.fromFile("/usr/share/dict/words").getLines.filter(_.size > 3).toSet
    val results = {
      val bytes = Hex decode message
      val hexes = Set(Hex.digits.map((c1) => Hex.digits.map((c0) => c1.toString + c0.toString))).flatten.flatten //derps
      val against = hexes.map(Hex decode _ apply 0)
      against.map((a) => bytes.map((b) => b ^ a toByte)).map(new String(_))
    }
    def containsWord(s: String) = s.split("\\s+").toSet exists words

    results.filter(containsWord _).foreach(println)
  }
}
