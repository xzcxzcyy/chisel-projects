package crc

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._
import org.scalatest.Matchers

class CRCParallelTest extends FreeSpec with ChiselScalatestTester with Matchers {
  "CRCParallel should pass" in {
    test(new CRCParallel(3, 29)) { c =>
      c.io.in.poke(utils.Bits.asBinary("110").U)
      c.io.out.expect(utils.Bits.asBinary("1001").U)
    }
  }
}
