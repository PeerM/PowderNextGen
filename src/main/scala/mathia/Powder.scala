package mathia

import mathia.Util.{linearizedCord, tupleToApacheVec, vector2DClamp}
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D

class Powder(val pos: Vector2D, val velocity: Vector2D) {
  def getAtomicWeight = Powder.atomicWeight

  def update(cell: Cell, deltaTime: Float, height: Float, width: Float) = {
    // TODO add drag
    //    val vecFromCellMiddle = pos subtract cell.middle
    val acceleration = cell.pressureVec // function of cell.pressure, pos
    val accelerationWithGravity = acceleration add Powder.gravity
    val newVelocity = velocity add(deltaTime, accelerationWithGravity) //  function of acceleration and velocity
    val newPos = pos add newVelocity
    val posClamped = vector2DClamp(newPos, height, width)
    new Powder(posClamped, newVelocity)
  }

  def cellCord(cellSize: Float, lineSize: Int): Int = {
    linearizedCord((pos.getX / cellSize).toInt, (pos.getY / cellSize).toInt, lineSize)
  }


  override def toString = s"Powder($pos, $velocity)"
}

object Powder {
  val atomicWeight: Float = 1
  val gravity = new Vector2D(0, 0.5)

  def apply(pos: Vector2D): Powder = new Powder(pos, (0f, 0f))
}
