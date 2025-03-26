package ch.makery.game.view
import ch.makery.game.MainApp
import scalafxml.core.macros.sfxml
import ch.makery.game.model.GuineaPig
@sfxml
class WelcomePageController extends DisplayGameInstructions{
  //To start the game
  def startGame():Unit = {
    val guineaPigInfo = GuineaPig.getGuineaPigData()
    //If player is new, let them select a guinea pig
    if (guineaPigInfo.isEmpty) {
      //Display guinea pig options
      MainApp.showChooseGuineaPig()
    }
    //If player is an existing player, update game data
    else {
      val guineaPig = new GuineaPig()
      //Update guinea pig's hunger and hygiene
      val (_, hunger, hygiene,_, _) = guineaPig.updateData()

      //When the game starts, hunger and hygiene is reduced by 5
      //[to allow users to have activities to do when the game starts]
      val currentHungerValue = hunger
      val newHungerValue = guineaPig.hungerScale.decreaseValue(currentHungerValue, 5)
      guineaPig.updateHunger(newHungerValue)

      val currentHygieneValue = hygiene
      val newHygieneValue = guineaPig.hygieneScale.decreaseValue(currentHygieneValue, 5)
      guineaPig.updateHygiene(newHygieneValue)

      //Show the guinea pig house
      MainApp.showGuineaPigHouse()
    }
  }

  //To show instructions
  def showInstructions():Unit={
    //Display the instructions
    displayInstructions()
  }

  //To quit game
  def quitGame():Unit={
    //Exit the game
    System.exit(0)
  }
}