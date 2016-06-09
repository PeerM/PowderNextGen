package mathia

import org.scalatest.FunSuite

class Util_Test extends FunSuite {

  test("testLinearizedNeighbours") {
    val expected: Vector[(Any, Int)] = Vector(
      ((-1, -1), 0), ((0, -1), 1), ((1, -1), 2),
      ((-1, 0), 3), ((1, 0), 5),
      ((-1, 1), 6), ((0, 1), 7), ((1, 1), 8))
    assert(Util.linearizedNeighbours(4, 3, 9) == expected)
  }

}
