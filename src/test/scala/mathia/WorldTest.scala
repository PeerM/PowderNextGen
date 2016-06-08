package mathia

import mathia.Util._
import org.scalactic.Tolerance._
import org.scalatest.FunSuite

class WorldTest extends FunSuite {

  test("testTick") {
    val particles = Vector(
      Powder((0.4f, 0.4f)),
      Powder((0.6f, 0.8f)),
      Powder((1.6f, 0.4f))
    )

    val world = new World(particles)
    val subject = world.tick
    assert(1 == 1)
  }
  test("testGravity") {
    val particles = Vector(
      Powder((25f, 0.5f))
    )

    val world = new World(particles)
    val subject = world.tick
    assert(subject.head.pos.getX === 25.0 +- 0.002)
    assert(subject.head.pos.getY > 0.5)
  }

}
