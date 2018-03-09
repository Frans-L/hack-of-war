package game.main

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import game.{GameElement, World}
import game.loader.GameTextures
import game.objects.{GameObject, StaticObject}

import scala.collection.mutable


object Map {

  val mapBorder = GameTextures.Units.mapBorder
  val mapBorderShort = GameTextures.Units.mapBorderShort
  val mapBorderWide = GameTextures.Units.mapBorderWide
}

/**
  * Created by Frans on 06/03/2018.
  */
class Map(world: World, textures: GameTextures) extends GameElement {

  private val elements: mutable.Buffer[StaticObject] = mutable.Buffer[StaticObject]()

  private val collAccuracy = 20 //collisionAccuracy
  private val collMap = Array.ofDim[Boolean](
    world.maxWidth / collAccuracy + 1,
    world.maxHeight / collAccuracy + 1)

  initializeMap()
  createCollisionMap()

  override def update(): Unit = ???

  override def draw(shapeRender: ShapeRenderer): Unit = ???

  override def draw(batch: Batch): Unit = {
    elements.foreach(_.draw(batch))
  }

  //returns true if collided
  def collide(x: Float, y: Float): Boolean = {
    if (world.isInside(x + 0.5f, y + 0.5f))
      collMap(
        ((-world.maxLeft + x) / collAccuracy + 0.5f).toInt)(
        ((-world.maxDown + y) / collAccuracy + 0.5f).toInt)
    else
      false
  }

  //creates the static collision map from 'elements'
  private def createCollisionMap(): Unit = {
    for (x <- collMap.indices) {
      for (y <- collMap.head.indices) {
        collMap(x)(y) = elements.exists(
          _.contains(world.maxLeft + x * collAccuracy, world.maxDown + y * collAccuracy))
      }
    }
  }

  //creates the map
  private def initializeMap(): Unit = {

    //Down border
    var height: Float = 200 + 180
    var y: Float = world.maxDown
    var x: Float = world.maxLeft
    for (i <- 0 to 8) {
      elements += new StaticObject(
        x, y,
        345 - ((i % 2) * 165), height, 1, 0,
        textures.atlas.createSprite(Map.mapBorder))

      x = elements.last.nextToX
    }

    //Up border
    height = 50 + 180
    y = world.maxUp - height
    x = world.maxLeft
    for (i <- 0 to 8) {
      elements += new StaticObject(
        x, y,
        345 - ((i % 2) * 165), height, 1, 0,
        textures.atlas.createSprite(Map.mapBorder))

      x = elements.last.nextToX
    }


    //middle
    y = 0
    x = world.left + 345 + 180
    for (i <- 0 to 1) {
      elements += new StaticObject(
        x, y,
        435, 75, 1, 0,
        textures.atlas.createSprite(Map.mapBorder))

      x = elements.last.nextToX
    }

  }
}
