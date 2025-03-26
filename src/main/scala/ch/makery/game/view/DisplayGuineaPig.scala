package ch.makery.game.view
import ch.makery.game.model.GuineaPig
import scalafx.scene.image.ImageView

trait DisplayGuineaPig{
  val guineaPigImage: ImageView

  //To display the guinea pig
  def displayGuineaPig(guineaPig: GuineaPig): Unit = {
    //Update information about guinea pig's color and their hygiene
    val (color, _, hygiene, _, _) = guineaPig.updateData()
    //Call generateGuineaPigImage from Guinea Pig class to derive guinea pig image
    val derivedImage = guineaPig.generateGuineaPigImage(color, hygiene)
    guineaPigImage.image = derivedImage
  }
}

