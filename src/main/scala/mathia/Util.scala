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

  def vector2DClamp(vec: Vector2D, width: Float, height: Float): Vector2D = {
    val clampedX = min(max(0, vec.getX), width)
    val clampedY = min(max(0, vec.getY), height)
    if (clampedX == vec.getX && clampedY == vec.getY) vec else new Vector2D(clampedX, clampedY)
  }
}
