package ch.makery.game.model
import scalafx.scene.image.Image

//Abstract class for Food
abstract class Food {
  val name: String
  val image: Image
  val price: Double
  val energyLevel: Int
}
