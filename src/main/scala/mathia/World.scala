package mathia

import mathia.Util._
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D

import scala.collection.JavaConverters._
import scala.util.Random

class World(val particles: Iterable[Powder], val cells: IndexedSeq[Cell]) {
  val gridSize = 1
  val dimensions = (50, 50)
  val (height, width) = dimensions
  val lineSize: Int = width
  val deltaTimePerTick = 0.1f

  val numCells = height / gridSize + width / gridSize

  def tick = {

    val newCells = particles
      .groupBy(p => p.cellCord(gridSize, lineSize))
      .map(pair => Cell(pair._2, cells, pair._1, lineSize, height * width)).toIndexedSeq
    val newParticles = newCells
      .flatMap(cell => cell.update(deltaTimePerTick, width, height))
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

  def initial(particles: Iterable[Powder]) = new World(particles, Vector(new Cell(Vector(), new Vector2D(0, 0), 0)))
}
