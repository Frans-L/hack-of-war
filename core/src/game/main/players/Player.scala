package game.main.players

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import game.GameElement
import game.main.Card
import game.main.physics.PhysicsWorld
import game.main.units.Soldier

import scala.collection.mutable

/**
  * Created by Frans on 06/03/2018.
  */
abstract class Player(physWorld: PhysicsWorld, index: Int) extends GameElement {

  val colorIndex: Int //color of the textures
  val enemies: mutable.Buffer[Player] = mutable.Buffer.empty[Player] //the enemy of this player

  private val deck: mutable.Buffer[Card] = mutable.Buffer[Card]()
  val hand: mutable.Buffer[Card] = mutable.Buffer[Card]()

  override def update(): Unit = {

    //remove used cards
    for (i <- hand.indices.reverse)
      if (hand(i).used) hand.remove(i)

  }

  /** Returns true is succeeded */
  def useCard(card: Card, posX: Float, posY: Float): Boolean = {

    //the card can be used
    if (!physWorld.map.collide(posX, posY)) {
      true
    } else {
      false
    }
  }

  /** Spawns a new unit */
  def spawnUnit(x: Float, y: Float): Unit = {
    Soldier.create(this, physWorld, x, y)
  }

  override def draw(shapeRender: ShapeRenderer): Unit = {
  }

  override def draw(batch: Batch): Unit = {
  }

  /** Returns enemies buffer[Player] as buffer[GameElement] */
  def enemiesAsGameElement: mutable.Buffer[GameElement] = enemies.asInstanceOf[mutable.Buffer[GameElement]]

}