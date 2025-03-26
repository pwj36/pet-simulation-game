package ch.makery.game.model
import scalafx.scene.image.Image

//Abstract class for accessory
abstract class Accessory{
  val name: String
  val image: Image
  val price: Double
}