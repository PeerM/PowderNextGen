package mathia

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D

class Cell(val middle: Vector2D, val particles: Iterable[Powder]) {
  val pressure = {
    val nrParicles = particles.size
    if (nrParicles < 1) 0 else nrParicles - 1
  } // TODO think about using the pressure of neighboring cells to influence this

  def update(deltaTime: Float, width: Float, height: Float) =
    particles.map(p => p.update(this, deltaTime, width, height))
}
