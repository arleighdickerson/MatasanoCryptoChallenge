package set1
import Codecs._
import scala.io.Source
object Challenge6 {
  val message = Base64.decode(Source
    .fromURL("http://cryptopals.com/static/challenge-data/6.txt")
    .getLines
    .foldLeft("")(_ + _))

  /**
   * @see http://www.tautvidas.com/blog/2013/07/compute-hamming-distance-of-byte-arrays/
   */
  def hammingDistance(from: Array[Byte], to: Array[Byte]) = {
    def countSetBits(byte: Byte): Int = (0 to 7).map((i: Int) => (byte >>> i) & 1).sum
    (from.zip(to).map((t) => countSetBits((t._1 ^ t._2).toByte))).sum //xor like bitwise neq
  }

  def distances(slices: Seq[Array[Byte]]) = for (i <- (0 to slices.length - 2))
    yield hammingDistance(slices.apply(i), slices.apply(i + 1))

  def weightedDistance(keyLength: Int) = {
    val lengths = distances(message.grouped(keyLength).toSeq)
    lengths.sum.toDouble / lengths.size / keyLength
  }

  val keyLengths = (2 to 40).sortBy(weightedDistance _)

  def main(args: Array[String]): Unit = {
    keyLengths.take(5).map(findKey _) foreach ((key) => {
      println(decrypt(key))
      println("-" * 80)
    })
  }

  def decrypt(key: Array[Byte]) = {
    val repeatedKey = Stream.continually(key.toStream).flatten.take(message.length)
    new String(message.zip(repeatedKey).map((t) => (t._1 ^ t._2) toByte))
  }

  def blocks(keySize: Int, message: Array[Byte] = this.message): Seq[Array[Byte]] = {
    if (message.length % keySize == 0)
      message.grouped(keySize).toSeq
    else
      blocks(keySize, message :+ (0.toByte))
  }

  def findKey(keySize: Int) = blocks(keySize)
    .transpose.map(findKeyForBlock _).toArray

  def findKeyForBlock(block: Seq[Byte]): Byte = {
    val possibleKeys = (0 to 255) map (_ toByte)
    def score(key: Byte) = {
      val result = block
        .zip(Array.fill(block.length)(key))
        .map((t) => t._1 ^ t._2 toByte) //xor them
      PlaintextScoring.byCharacters(new String(result toArray))
    }
    possibleKeys.map((key) => key -> score(key)).sortBy({ _._2 }).last._1
  }
}
