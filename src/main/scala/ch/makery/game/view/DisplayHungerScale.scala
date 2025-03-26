package ch.makery.game.view
import ch.makery.game.model.GuineaPig
import scalafx.scene.control.ProgressBar

trait DisplayHungerScale{
  val hungerLevel: ProgressBar

  //To display the hunger scale
  def displayHungerScale(guineaPig: GuineaPig): Unit = {
    //Update information about the guinea pig's hunger
    val (_, hunger, _, _, _) = guineaPig.updateData()
    //Divide guinea pig's hunger by 100 to set progress bar
    val hygieneBar = hunger / 100.0
    hungerLevel.setProgress(hygieneBar)
  }
}