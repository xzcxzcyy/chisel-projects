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

  var checkCode = Wire(0.U(r.W))

  var i = 0
  for (i <- 0 until k) {
    checkCode = checkCode ^ Mux(io.in(i), BinaryDiv.divide(1 << (r + i), gx).U(r.W), 0.U(r.W))
  }
  
  io.out := checkCode
}