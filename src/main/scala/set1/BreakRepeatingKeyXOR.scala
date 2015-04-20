package set1
import Codecs._
object BreakRepeatingKeyXOR {
  val message = scala.io.Source.fromURL("http://cryptopals.com/static/challenge-data/6.txt")
    .getLines
    .foldLeft("")(_ + _)
    .getBytes("US-ASCII")

  /**
   * see http://www.tautvidas.com/blog/2013/07/compute-hamming-distance-of-byte-arrays/
   */
  def hammingDistance(from: Array[Byte], to: Array[Byte]) = {
    def countSet(byte: Byte): Int = (0 to 7).map((i: Int) => (byte >>> i) & 1).sum
    (from.zip(to).map((t) => countSet((t._1 ^ t._2).toByte))).sum //xor like bitwise neq
  }

  def distances(slices: Seq[Array[Byte]]) = {
    for (i <- (0 to slices.length - 2)) yield hammingDistance(slices.apply(i), slices.apply(i + 1))
  }

  def arithmeticMean(ints: Seq[Int]) = ints.sum.toFloat / ints.length

  def main(args: Array[String]): Unit = { 
    val meanDistance = arithmeticMean _ compose distances _ compose ((k: Int) => message.grouped(k).toSeq)
    val rankedKeyLengths = (2 to 40)
      .map((keySize: Int) => keySize -> meanDistance(keySize) / keySize)
      .toList.sortBy { _._2 } map ((t) => t._1)
    println(rankedKeyLengths)
  }
}
