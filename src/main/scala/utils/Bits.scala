package utils

object Bits {
  def asBinary(str: String): Int = {
    var result = 0
    for (s <- str) {
      result <<= 1
      if (s == '1') {
        result |= 1
      }
    }
    return result
  }
}