package set1
import Codecs._

object SingleByteXOR {
  val message = "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736"
  val digits = Array[Char]('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')
  val words = scala.io.Source.fromFile("/usr/share/dict/words").getLines.filter(_.size > 3).toSet
  def results = {
    val bytes = Hex.decode(message)
    val hexes = Set(digits.map((c1) => digits.map((c0) => c1.toString + c0.toString))).flatten.flatten //derps
    val against = hexes.map(Hex.decode(_).apply(0))
    against.map((a) => bytes.map((b) => (b ^ a).toByte)).map(new String(_, "US-ASCII"))
  }
  def containsWord(s: String) = s.split("\\s+").toSet exists words
  def search = results.filter(containsWord(_)).foreach(println)
}
