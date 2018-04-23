package game.main.units

import com.badlogic.gdx.math.Vector2
import game.GameElement
import game.loader.{GameTextures, UnitTextures}
import game.main.physics.PhysicsWorld
import game.main.physics.collision.{CircleBody, CollisionBody}
import game.main.objects.{BulletObject, UnitObject}
import game.util.{Utils, Vector2e}

object BasicBullet {

  val textures: UnitTextures = GameTextures.Units.BaseBullet

  //size info
  private val scale = 1.0f
  val radius: Float = 20f / 2f / scale

  def create(owner: GameElement, physWorld: PhysicsWorld,
             pos: Vector2, velocity: Vector2,
             colorIndex: Int): BulletObject = {

    val body: CollisionBody = new CircleBody(radius)
    //Utils.rectangleCollBody(0, 0, w.toInt, h.toInt)

    val bullet: BulletObject = new BulletObject(
      textures, colorIndex,
      Vector2e(radius * 2f, radius * 2f),
      owner, physWorld, body,
      pos, velocity.scl(1f)
    )

    bullet.collToMe = false //make sure that others object doesn't collide with bullet

    bullet
  }
}
