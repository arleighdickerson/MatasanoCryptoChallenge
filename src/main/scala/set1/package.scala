package object set1 {
  implicit class RichString(str: String) {
    def countOccurrences(substr: String): Int = {
      @scala.annotation.tailrec
      def count(pos: Int, c: Int): Int = {
        val idx = str indexOf (substr, pos)
        if (idx == -1) c else count(idx + substr.size, c + 1)
      }
      count(0, 0)
    }
  }
}
