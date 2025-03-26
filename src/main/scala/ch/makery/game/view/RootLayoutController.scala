package ch.makery.game.view
import ch.makery.game.MainApp
import scalafxml.core.macros.sfxml
@sfxml
class RootLayoutController extends DisplayGameInstructions{

  //To return back to the main page
  def returnMainPage():Unit={
    //Display main page
    MainApp.showWelcomePage()
  }

  //To show the game instructions
  def showInstructions(): Unit = {
    //Display the game information
    displayInstructions()
  }

  //To close the application
  def closeProgram(): Unit = {
    //Exit program
    System.exit(0)
  }
}