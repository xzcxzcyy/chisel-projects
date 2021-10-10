package crc

object BinaryDiv {
  def getWidth(x: Int): Int = {
    var a: Int = 0
    var num = x
    while (num != 0) {
      a += 1
      num = num >> 1
    }
    return a
  }

  def getDigitByPosition(num: Int, pos: Int) = {
    (num >> pos) & 1
  }

  def getSeg(num: Int, lo: Int, hi: Int) = {
    val mask = ~((~0 >> (hi - lo + 1)) << (hi - lo + 1))
    (num >> lo) & mask
  }

  def divide(x: Int, y: Int): Int = {
    val widthOfX = getWidth(x)
    val widthOfY = getWidth(y)
    if (widthOfX < widthOfY) {
      return 0
    }
    var i = widthOfX - widthOfY
    var seg = getSeg(x, i, widthOfX - 1)
    while (i > 0) {
      if (getDigitByPosition(seg, widthOfY - 1) == 1) {
        seg = ((seg ^ y) << 1) | getDigitByPosition(x, i - 1)
      } else {
        seg = (seg << 1) + getDigitByPosition(x, i - 1)
      }
      i -= 1
    }
    if (getDigitByPosition(seg, widthOfY - 1) == 1) {
      seg ^ y
    } else {
      seg
    }
  }
}
