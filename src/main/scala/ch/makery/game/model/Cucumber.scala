package ch.makery.game.model
import scalafx.scene.image.Image

//Cucumber is a subclass of abstract class Food
class Cucumber extends Food {
  val name = "Cucumber"
  val image = new Image(getClass.getResourceAsStream("/Cucumber.png"))
  val price = 40
  val energyLevel = 20
}
