package mathia

import mathia.Util._

import scala.collection.JavaConverters._
import scala.util.Random

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

  def randomParticles(nr: Int) = {
    val rands = Range(0, nr).map((_) => Powder((Random.nextFloat() * width, Random.nextFloat() * width)))
    new World(particles ++ rands)
  }

  def getParticles = particles.asJava
}

object World {
  def constructJava(particles: java.util.Collection[Powder]) = new World(particles.asScala)
}
