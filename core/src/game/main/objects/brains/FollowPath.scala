package game.main.objects.brains

import game.main.gameMap.{IconPath, Path}
import game.main.objects.UnitObject

/** Updates the unitObject's moveTarget. */
class FollowPath(var path: Path, val acceptDist: Float) extends Brain {

  private var targetI: Int = 0 //current target index

  override def update(unitObject: UnitObject): Unit = {

    //if at the checkpoint, get next checkpoint
    if (unitObject.pos.dst2(path(targetI)) <= acceptDist * acceptDist) {
        targetI = math.min(targetI + 1, path.length - 1)
    }

    unitObject.moveTarget.set(path(targetI))
  }

}