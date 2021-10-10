package crc

import chisel3._

class CRCParallel(k: Int, gx: Int) extends Module {
  val r = {
    var r = 0;
    var d = gx;
    while (d > 0) {
      r += 1
      d >>= 1
    }
    r - 1
  }
  val n = k + r
  
  val io = IO(new Bundle {
    val in = Input(UInt(k.W))
    val out = Output(UInt(r.W))
  })
  // val table = new Array[List[Int]](r)
  val table = new Array[Bool](r)
  var i = 0
  
  for (i <- 0 until r) {
    var j = 0
    var empty: Boolean = true
    for (j <- 0 until k) {
      val remainder = BinaryDiv.divide(1 << (r + j), gx)
      if (BinaryDiv.getDigitByPosition(remainder, i) == 1) {
        if (empty) {
          table(i) = io.in(j)
          empty = false
        } else {
          table(i) = table(i) ^ io.in(j)
        }
      }
    }
  }
  var ioOutVec = Wire(Vec(r, Bool()))
  for (i <- 0 until r) {
    ioOutVec(i) := table(i)
  }
  io.out := ioOutVec.asUInt()

  // var checkCode = 0.U(r.W)

  // var i = 0
  // for (i <- 0 until k) {
  //   checkCode = checkCode ^ Mux(io.in(i), BinaryDiv.divide(1 << (r + i), gx).U(r.W), 0.U(r.W))
  // }
  
  // io.out := checkCode
}