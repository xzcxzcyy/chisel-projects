package triple_xor

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class TripleXorTest extends FreeSpec with ChiselScalatestTester {
  "Multiple Xor Functor should pass" in {
    test(new TripleXor(16)) { dut =>
      var i = 0
      for (i <- 0 until (1 << 16)) {
        dut.io.in.poke(i.U)
        var res = 0
        var ii = i
        while (ii != 0) {
          res = res ^ (ii & 1)
          ii = (ii >> 1)
        }
        dut.io.out.expect(res.B)
      }
    }
  }
}