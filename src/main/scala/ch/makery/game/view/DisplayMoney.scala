package ch.makery.game.view
import ch.makery.game.model.GuineaPig
import scalafx.scene.control.Label

trait DisplayMoney{
  val moneyLabel: Label
    //To display the amount of money the guinea pig has
    def displayMoney(guineaPig: GuineaPig): Unit = {
      //Update information about the amount of money the guinea pig has
      val (_, _, _, money,_) = guineaPig.updateData()
      //Format the money to display
      moneyLabel.text = f"RM%%.2f".format(money)
    }

}