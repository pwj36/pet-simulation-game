package ch.makery.game.model
import scalafx.scene.image.Image

//Tomato is a subclass of abstract class Food
class Tomato extends Food {
  val name = "Tomato"
  val image = new Image(getClass.getResourceAsStream("/Tomato.png"))
  val price = 20
  val energyLevel = 10
}