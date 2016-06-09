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
    val world = World.initial(particles)
    val subject = world.tick
    val first = subject.particles.head
    val second = subject.particles.tail.head
    val third = subject.particles.tail.tail.head
    assert(1 == 1)
  }
  test("testGravity") {
    val particles = Vector(
      Powder((25f, 0.5f))
    )

    val world = World.initial(particles)
    val subject = world.tick.particles
    assert(subject.head.pos.getX === 25.0 +- 0.002)
    assert(subject.head.pos.getY > 0.5)
  }
  test("hill") {
    val particles = Vector(
      Powder((0.5f, 49.5f)),
      Powder((0.5f, 49.5f)),
      Powder((1.5f, 49.5f)),
      Powder((1.5f, 49.5f)),
      Powder((1.0f, 48.5f)),
      Powder((2.5f, 49.5f)),
      Powder((2.5f, 49.5f)),
      Powder((5f, 10.5f))
    )
    val world = World.initial(particles)
    val subject = world.tick.tick.tick.tick
    val parts = subject.particles.toIndexedSeq
    val count = parts.count(powder => (powder.pos.getY - 49).abs < 0.3)
    assert(count == 1)
  }

}
