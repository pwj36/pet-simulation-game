package ch.makery.game.model
import scalafx.scene.image.Image

//Ribbon is a subclass of abstract class Accessory
class Ribbon extends Accessory {
  val name = "Ribbon"
  val image = new Image(getClass.getResourceAsStream("/Ribbon.png"))
  val price = 200
}