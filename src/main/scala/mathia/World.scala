package mathia

import mathia.Util._

import scala.collection.JavaConverters._

class World(val particles: Iterable[Powder]) {
  val gridSize = 1
  val dimensions = (50, 50)
  val (height, width) = dimensions
  val lineSize: Int = width
  val deltaTimePerTick = 0.3f

  val numCells = height / gridSize + width / gridSize

  def tick = {
    particles
      .groupBy(p => p.cellCord(gridSize, lineSize))
      .map(pair => new Cell(linearizedCordToMiddle(pair._1, lineSize, gridSize), pair._2))
      .flatMap(cell => cell.update(deltaTimePerTick, width, height))

    // sort particles into cells
    // update each cell
    // replace the old particles with the new
  }

  def update = new World(tick)
}

object World {
  def constructJava(particles: java.util.Collection[Powder]) = new World(particles.asScala)
}
