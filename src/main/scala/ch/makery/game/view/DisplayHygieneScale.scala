package ch.makery.game.view
import ch.makery.game.model.GuineaPig
import scalafx.scene.control.ProgressBar

trait DisplayHygieneScale{
  val hygieneLevel: ProgressBar

  //To display hygiene scale
  def displayHygieneScale(guineaPig: GuineaPig): Unit = {
    //Update information about the guinea pig's hygiene
    val (_, _, hygiene, _, _) = guineaPig.updateData()
    //Divide guinea pig's hygiene by 100 to set progress bar
    val hygieneBar = hygiene / 100.0
    hygieneLevel.setProgress(hygieneBar)
  }
}