package set1

import Codecs._

object DetectSingleCharXOR {
  def main(args: Array[String]) = {
    val messages = scala.io.Source.fromURL("http://cryptopals.com/static/challenge-data/4.txt").getLines.toList.map(Hex.decode(_))
    val words = scala.io.Source.fromFile("/usr/share/dict/words").getLines.filter(_.size > 3).toSet
    val digits = Array[Char]('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')
    val bytes = Set(digits.map((c1) => digits.map((c0) => c1.toString + c0.toString)))
      .flatten
      .flatten
      .map(Hex decode _ apply 0)
    val results = messages.map((m) => bytes.map((b) => m.map((a) => (a ^ b).toByte))).flatten.map(new String(_, "US-ASCII"))
    def containsWord(s: String) = s.split("\\s+").toSet exists words
    results.filter(containsWord _) foreach (println)
  }
}
