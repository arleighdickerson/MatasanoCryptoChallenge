package us.arleighdickerson.cryptochallenge
import Codecs._
import scala.io.Source
object Challenge8 {
  val messages = Source.fromURL("http://cryptopals.com/static/challenge-data/8.txt").getLines.toList

  def countRepititions(message: String, length: Int): Int = {
    val substrings = message.inits.flatMap(_.tails.toList.init).filter(_.length == length).toSet
    substrings.map((substring) => message.countOccurrences(substring) - 1).sum
  }

  def getRankings(length: Int) = messages.par.map((m) => messages.indexOf(m) -> countRepititions(m, length))
    .toList
    .sortBy({ -_._2 })

  def main(args: Array[String]): Unit = {
    println("Repitition Rankings")
    println("block length: (message index -> occurrences),...")
    (2 to 16).map((i) => println(i + ": " + getRankings(i))) //clearly the message on line 133
  }
}
