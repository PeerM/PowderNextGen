package mathia

import mathia.Util._

import scala.collection.JavaConverters._
import scala.util.Random

class World(val particles: Iterable[Powder], val cells: Map[Int, Cell]) {
  val gridSize = 1
  val dimensions = (50, 50)
  val (height, width) = dimensions
  val lineSize: Int = width
  val deltaTimePerTick = 0.5f

  val numCells = height / gridSize + width / gridSize

  def tick = {

    val newCells: Map[Int, Cell] = particles
      .groupBy(p => p.cellCord(gridSize, lineSize))
      .map(pair => (pair._1, Cell(pair._2, cells, pair._1, lineSize, height * width)))
    val newParticles = newCells.flatMap(pair => pair._2.update(deltaTimePerTick, width, height))
    new World(newParticles, newCells)
    // sort particles into cells
    // update each cell
    // replace the old particles with the new
  }

  def randomParticles(nr: Int) = {
    val rands = Range(0, nr).map((_) => Powder((Random.nextFloat() * width, Random.nextFloat() * width)))
    new World(particles ++ rands, cells)
  }

  def getParticles = particles.asJava

  def getCells = cells.asJava
}

object World {
  def constructJava(particles: java.util.Collection[Powder]) = initial(particles.asScala)

  def initial(particles: Iterable[Powder]) = new World(particles, Map())
}
