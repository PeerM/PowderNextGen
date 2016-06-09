package mathia

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D

import scala.math.{max, min}

object Util {
  implicit def tupleToApacheVec(xy: (Float, Float)): Vector2D = {
    val (x, y) = xy
    new Vector2D(x, y)
  }

  def linearizedCord(x: Int, y: Int, lineSize: Int) = x + y * lineSize

  def linearizedCordToMiddle(linearizedCord: Int, lineSize: Int, gridSize: Float) = {
    val xCord = linearizedCord % lineSize
    val yCord = (linearizedCord - xCord) / lineSize
    val halfGridSize = gridSize / 2
    new Vector2D((xCord * gridSize) + halfGridSize, (yCord * gridSize) + halfGridSize)
  }

  private def linearNeigh(linearCord: Int, pair: (Int, Int), lineSize: Int): Int = {
    val (x, y) = pair
    val offset = x + y * lineSize
    linearCord + offset
  }

  val simpleOffsets: Vector[(Int, Int)] = Vector((-1, -1), (0, -1), (1, -1), (-1, 0), (1, 0), (-1, 1), (0, 1), (1, 1))

  def linearizedNeighbours(linearizedCord: Int, lineSize: Int, totalSize: Int) = {
    simpleOffsets.map(pair => (pair, linearNeigh(linearizedCord, pair, lineSize)))
      .filter(pair => pair._2 >= 0 && pair._2 < totalSize)
  }

  def vector2DClamp(vec: Vector2D, width: Float, height: Float): Vector2D = {
    val clampedX = min(max(0, vec.getX), width)
    val clampedY = min(max(0, vec.getY), height)
    if (clampedX == vec.getX && clampedY == vec.getY) vec else new Vector2D(clampedX, clampedY)
  }

  def allMax(accumulator: (Float, List[((Int, Int), Float)]), element: ((Int, Int), Float)): (Float, List[((Int, Int), Float)]) = {
    val ((cord), pres) = element
    val (max, elements) = accumulator
    if (pres > max) {
      (pres, List(element))
    } else if
  }

}
