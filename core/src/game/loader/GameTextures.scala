package game.loader

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.AssetLoader
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader.FreeTypeFontLoaderParameter
import com.badlogic.gdx.graphics.g2d.{BitmapFont, TextureAtlas}

/**
  * Created by Frans on 01/03/2018.
  */
class GameTextures extends Loadable {

  val load: Vector[(String, Class[_])] = Vector((GameTextures.atlasName, classOf[TextureAtlas]))

  val loadFont: Vector[(String, Class[BitmapFont], FreeTypeFontLoaderParameter)] = Vector(
    (GameTextures.Font.Normal20.name, classOf[BitmapFont], GameTextures.Font.Normal20.params)
  )

  var atlas: TextureAtlas = _

  object Fonts {
    var normal20: BitmapFont = _
  }

  var font: BitmapFont = _

  override def finished(manager: AssetManager): Unit = {
    atlas = manager.get(load(0)._1)
    Fonts.normal20 = manager.get(loadFont(0)._1)
    font = new BitmapFont()
  }

}


object GameTextures {


  //null value at start =>
  //errors will appear when game starts, if this not set to be something
  var defaultTextures: GameTextures = _
  var defaultUITextures: GameTextures = _

  val atlasName = "graphics.atlas"

  object Jei {
    val a = 1
  }

  object Font {

    object Normal20 {
      val name = "normal20.ttf"
      val params = new FreeTypeFontLoaderParameter()
      params.fontFileName = "fonts/DoHyeon.ttf"
      params.fontParameters.size = 45
      params.fontParameters.color = Color.WHITE

      /*
      params.fontParameters.borderWidth = 1
      params.fontParameters.borderColor = Color.valueOf("#6b84ff")
      */

      params.fontParameters.shadowColor = Color.BLACK
      params.fontParameters.shadowOffsetX = 2
      params.fontParameters.shadowOffsetY = 2

      params.fontParameters.minFilter = TextureFilter.MipMapLinearLinear
      params.fontParameters.magFilter = TextureFilter.Linear
      params.fontParameters.genMipMaps = true
    }

  }

  object Units {
    val unit1 = Seq("unit1Blue", "unit1Red")
    val bullet1 = Seq("bullet1Blue", "bullet1Red")
    val card1 = "card1"
    val mapBorderShort = "mapBorderShort"
    val mapBorderWide = "mapBorderWide"

    val mapBorder = "mapBorder"
    val mapCorner = "mapCorner"
    val mapTrenchCorner = "mapTrenchCorner"
    val mapMiddleCorner = "mapMiddleCorner"

    val pathPoint = "pointCircle"
  }

}
