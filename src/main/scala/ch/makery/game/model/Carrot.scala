package ch.makery.game.model
import scalafx.scene.image.Image

//Carrot is a subclass of abstract class Food
class Carrot extends Food {
  val name = "Carrot"
  val image = new Image(getClass.getResourceAsStream("/Carrot.png"))
  val price = 30
  val energyLevel = 15
}