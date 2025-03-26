package ch.makery.game.view
import ch.makery.game.MainApp
import ch.makery.game.model.GuineaPig
import scalafxml.core.macros.sfxml
import scalafx.scene.input.{KeyCode, KeyEvent}
import scalafx.scene.image.ImageView
import scalafx.scene.layout.AnchorPane
@sfxml
class FashionShowController(private val parentPane: AnchorPane,
                            val guineaPigImage: ImageView,
                            val accessoryImage: ImageView)
                            extends DisplayGuineaPig with DisplayAccessory with DisplayInformationAlert{

  private val guineaPig = new GuineaPig()
  //Display guinea pig and its accessory
  displayGuineaPig(guineaPig)
  displayAccessory(guineaPig)

  //For guinea pig to walk the catwalk
  def pressOnKey(event: KeyEvent): Unit = {
    val maximumWidth = parentPane.scene.value.getWidth
    //Update latest guinea pig information about its hygiene and money
    val (_,_,hygiene, money,_) = guineaPig.updateData()

    event.code match {
      //Case where user enters right key
      case KeyCode.Right =>
        //If haven't finish walking
        if (guineaPigImage.layoutX() < maximumWidth) {
          guineaPigImage.layoutX = guineaPigImage.layoutX() + 15.0
          accessoryImage.layoutX = guineaPigImage.layoutX() + 15.0
        }
        //If finished walking
        else{
          var amount = 0
          //If guinea pig has low hygiene level, they will be given a lower salary
          if (guineaPig.hygieneScale.level<20){
            amount = 50
          }
          //Else give higher salary
          else {
            amount = 150
          }

          //Update the guinea pig's money
          val totalMoney = guineaPig.work(amount)
          val convertedTotalMoney = totalMoney.toInt
          guineaPig.updateMoney(convertedTotalMoney)

          //Display the information alert
          displayInfoAlert("Fashion Show", "Salary Earned!",
            s"Guinea Pig has just finished its catwalk! You will earn $amount!")

          MainApp.showGuineaPigHouse()
        }

      //Case where user enters any keys apart from right arrow key
      case _ => //Nothing happens
    }
  }

  //Go back to guinea pig house
  def goBack():Unit = {
    MainApp.showGuineaPigHouse()
  }

  //Show rules
  def showWorkRules():Unit={
    displayInfoAlert("How to Work?","WORK RULES: ",
      "1. Press on the right arrow key on your keyboard to walk the catwalk." +
      "\n2. You must finish walking the catwalk to gain your salary." +
      "\n3. If hygiene level is less than 20, salary earned would be RM 50.00." +
      "\n4. If hygiene level is more than or equals to 20, salary earned would be RM 150.00.")
  }
}
