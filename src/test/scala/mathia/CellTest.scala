package mathia

import mathia.Util._
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D
import org.scalatest.FunSuite

class CellTest extends FunSuite {

  test("testPressure") {
    val nullVector = new Vector2D(0, 0)
    val particles = Vector(
      Powder((1.5f, 1.5f)),
      Powder((1.5f, 1.5f))
    )
    val last = Vector(
      new Cell(Nil, nullVector, 1),
      new Cell(Nil, nullVector, 0),
      new Cell(Nil, nullVector, 1),
      new Cell(Nil, nullVector, 1),
      new Cell(Nil, nullVector, 1),
      new Cell(Nil, nullVector, 1),
      new Cell(Nil, nullVector, 1),
      new Cell(Nil, nullVector, 1)
    )
    val next: Cell = Cell(particles, last, 4, 3, 9)

    assert(next.pressureAbs == 2)
    assert(next.pressureVec.getX == 0)
    assert(next.pressureVec.getY < 0)
  }

}
