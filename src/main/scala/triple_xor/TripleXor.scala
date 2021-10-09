package triple_xor

import chisel3._

class TripleXor(n: Int) extends Module {
  val io = IO(new Bundle {
    val in = Input(UInt(n.W))
    val out = Output(Bool())
  })
  var tmp = io.in(0)
  
  var i = 0
  for (i <- 1 until n) {
    tmp = tmp ^ io.in(i)
  }
  
  io.out := tmp
}
