package ch.makery.game.view
import ch.makery.game.MainApp
import scalafxml.core.macros.sfxml
import ch.makery.game.model.GuineaPig
@sfxml
class ChooseGuineaPigController(){
  //Generate a new guinea pig
  private def generateGuineaPig(color: String):Unit={
    val userGuineaPig = new GuineaPig()
    userGuineaPig.color.value = color
    userGuineaPig.save()
    MainApp.showGuineaPigHouse()
  }

  //If player choose yellow color, generate yellow guinea pig
  def chooseYellow(): Unit ={
    generateGuineaPig("Yellow")
  }

  //If player choose black color, generate black guinea pig
  def chooseBlack():Unit = {
    generateGuineaPig("Black")
  }

  //If player choose grey color, generate grey guinea pig
  def chooseGrey():Unit = {
    generateGuineaPig("Grey")
  }

}