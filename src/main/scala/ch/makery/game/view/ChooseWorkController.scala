package ch.makery.game.view
import ch.makery.game.MainApp
import scalafxml.core.macros.sfxml
import scalafx.scene.image.{Image, ImageView}
@sfxml
class ChooseWorkController(private val fashionShowImage: ImageView,
                           private val mathCompetitionImage: ImageView) {
  //Set image for the 'fashion show' button
  private val fashionShowImageStream = getClass.getResourceAsStream("/FashionShow.png")
  private val fashionShowImg = new Image(fashionShowImageStream)
  fashionShowImage.image = fashionShowImg

  //Set image for the 'math competition' button
  private val mathCompetitionImageStream = getClass.getResourceAsStream("/MathCompetition.png")
  private val mathCompetitionImg = new Image(mathCompetitionImageStream)
  mathCompetitionImage.image = mathCompetitionImg

  //If player clicks on 'Fashion Show'
  def chooseFashionShow(): Unit = {
    //Display fashion show
    MainApp.showFashionShow()
  }

  //If player clicks on 'Math Competition'
  def chooseMathQuiz():Unit={
    //Display math competition
    MainApp.showMathQuiz()
  }

  //If player clicks on 'Go Back' button
  def goBack():Unit={
    //Go back to guinea pig house
    MainApp.showGuineaPigHouse()
  }
}