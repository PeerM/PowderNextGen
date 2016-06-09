package mathia

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D

class Cell(val particles: Iterable[Powder], val pressureVec: Vector2D, val pressureAbs: Float) {

  def update(deltaTime: Float, width: Float, height: Float) = {
    val map: Iterable[Powder] = particles.map(p => p.update(this, deltaTime, width, height))
    map
  }
}

object Cell {

  val pressureFactor = 1

  def apply(particles: Iterable[Powder], lastState: Map[Int, Cell], linearPos: Int, lineSize: Int, totalSize: Int): Cell = {
    val nrParicles = particles.size
    val pressure = if (nrParicles < 1) 0 else nrParicles
    val ord = Ordering.by((_: ((Int, Int), Float))._2)
    val maxOption = Util.linearizedNeighbours(linearPos, lineSize, math.min(lastState.size, totalSize))
      .map(pair => pressureOrDefault(lastState, pair))
      .map(pair => (pair._1, pressure - pair._2))
      .reduceOption(ord.max)
    val max = maxOption.getOrElse(((0, 0), 0f))
    new Cell(particles, new Vector2D(max._1._1 * pressureFactor, max._1._2 * pressureFactor), pressure)
  }

  def pressureOrDefault(lastState: Map[Int, Cell], pair: ((Int, Int), Int)): ((Int, Int), Float) = {
    (pair._1, lastState.get(pair._2).map(cell => cell.pressureAbs).orElse(Option(0f)).get)
  }
}

