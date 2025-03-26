package ch.makery.game.model
import scalafx.scene.image.Image

//Hat is a subclass of abstract class Accessory
class Hat extends Accessory {
  val name = "Hat"
  val image = new Image(getClass.getResourceAsStream("/Hat.png"))
  val price = 300
}
