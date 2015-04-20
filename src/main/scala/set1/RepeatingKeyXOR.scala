package set1

object RepeatingKeyXOR {
  def makeRepeatingKey(message: String, key: String, accum: String = ""): String = {
    if (accum.length < message.length)
      makeRepeatingKey(message, key, accum + key)
    else
      accum.substring(0, message.length)
  }

  def apply(message: String, key: String) = {
    Codecs.Hex encode message.getBytes("US-ASCII")
      .zip(makeRepeatingKey(message, key).getBytes("US-ASCII"))
      .map((t) => (t._1 ^ t._2).toByte)
  }

  def main(args: Array[String]) = {
    val message = "Burning 'em, if you ain't quick and nimble I go crazy when I hear a cymbal"
    val key = "ICE"
    val expected = "0b3637272a2b2e63622c2e69692a23693a2a3c6324202d623d63343c2a26226324272765272" +
      "a282b2f20430a652e2c652a3124333a653e2b2027630c692b20283165286326302e27282f"
    println("Expected: '" + expected + "'")
    println("Actual:   '" + this(message, key) + "'")
  }
}
