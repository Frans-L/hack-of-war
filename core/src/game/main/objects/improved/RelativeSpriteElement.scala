package game.main.objects.improved

import com.badlogic.gdx.graphics.g2d.{Batch, Sprite}
import com.badlogic.gdx.math.Vector2

class RelativeSpriteElement(sprite: Sprite, keepSize: Boolean,
                            val pos: Vector2, val scale: Vector2,
                            var angle: Float) extends SpriteElement(sprite, keepSize) {

  /** Updates the sprite loc */
  override def update(parent: GameObject, delta: Int) {
    sprite.setOrigin(parent.origin.x, parent.origin.y)

    if (keepSize) sprite.setPosition(parent.pos.x + pos.x - parent.origin.x,
      parent.pos.y + pos.y - parent.origin.y)
    else sprite.setBounds(parent.pos.x + pos.x - parent.origin.x,
      parent.pos.y + pos.y - parent.origin.y, parent.size.x, parent.size.y)

    sprite.setScale(parent.scale.x * scale.x, parent.scale.y * scale.y)
    sprite.setRotation(parent.angle)
  }
}