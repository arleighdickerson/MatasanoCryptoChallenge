package us.arleighdickerson

package object cryptochallenge {
  implicit class RichString(str: String) {
    def countOccurrences(substr: String): Int = {
      @scala.annotation.tailrec
      def count(pos: Int, c: Int): Int = {
        val idx = str indexOf (substr, pos)
        if (idx == -1) c else count(idx + substr.size, c + 1)
      }
      count(0, 0)
    }
    def countOccurrences(char: Char): Int = this.countOccurrences(char.toString)
  }

  type BYTES = Iterable[Byte]
  implicit class RichBytes[T <% BYTES](bytes: T) {
    def xor[S <% BYTES](against: S): Array[Byte] = bytes.zip(against).map((t) => t._1 ^ t._2 toByte).toArray
    def -[S <% BYTES](from: S) = {
      def countSetBits(byte: Byte): Int = (0 to 7).map((i: Int) => (byte >>> i) & 1).sum
      (from.zip(bytes).map((t) => countSetBits((t._1 ^ t._2).toByte))).sum //xor like bitwise neq
    }
  }
}
