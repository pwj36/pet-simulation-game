package ch.makery.game.view
import ch.makery.game.MainApp
import scalafxml.core.macros.sfxml
import ch.makery.game.model.GuineaPig
import scalafx.animation.TranslateTransition
import scalafx.scene.control.ProgressBar
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.media.{Media, MediaPlayer}
import scalafx.util.Duration
import scala.concurrent.duration.DurationInt
@sfxml
class BathroomController(private val showerIcon: ImageView,
                         private val combIcon: ImageView,
                         private val groomImage:ImageView,
                         val guineaPigImage: ImageView,
                         val hygieneLevel: ProgressBar)
  extends DisplayGuineaPig with DisplayHygieneScale with DisplayInformationAlert {

  private val guineaPig = new GuineaPig()
  //Display the guinea pig
  displayGuineaPig(guineaPig)
  //Display the hygiene scale
  displayHygieneScale(guineaPig)

  //Animation to shower the guinea pig
  private val showeringAnimation = new TranslateTransition(Duration(1.seconds.toMillis), groomImage) {
    autoReverse = true
    toX = 100
    toY = 80
    cycleCount = 5
  }

  //Animation to comb the guinea pig
  private val combingAnimation = new TranslateTransition(Duration(1.seconds.toMillis), groomImage) {
    autoReverse = true
    toX = -100
    toY = 80
    cycleCount = 5
  }

  //Animate the grooming behaviours (showering and combing)
  private def animateGrooming(animation: TranslateTransition): Unit = {
    animation.stop()
    animation.fromX = 0
    animation.fromY = 0
    animation.play()
  }

  //Showering image [Uses a sponge image]
  private val spongeImageStream = getClass.getResourceAsStream("/Sponge.png")
  private val spongeImage = new Image(spongeImageStream)

  //Showering audio
  private val showeringAudio = new Media(getClass.getResource("/ShoweringAudio.wav").toString)
  private val showeringSound = new MediaPlayer(showeringAudio)

  //Combing image
  private val combImageStream = getClass.getResourceAsStream("/Comb.png")
  private val combImage = new Image(combImageStream)

  //Combing audio
  private val combingAudio = new Media(getClass.getResource("/CombingHairAudio.wav").toString)
  private val combingSound = new MediaPlayer(combingAudio)

  //Place icon images on buttons
  showerIcon.image = spongeImage
  combIcon.image = combImage

  //When player clicks on the 'Shower' button
  def showShower(): Unit = {
    //Stop both audio [To prevent audio clash]
    combingSound.stop()
    showeringSound.stop()
    //Play showering audio
    showeringSound.play()

    //Animate the showering
    groomImage.image = spongeImage
    animateGrooming(showeringAnimation)

    //Update the guinea pig's hygiene value
    val hygieneValue = guineaPig.shower()
    guineaPig.updateHygiene(hygieneValue)

    //Re-display the guinea pig and the hygiene scale (as changes have been made)
    displayGuineaPig(guineaPig)
    displayHygieneScale(guineaPig)

    //Display the alert that guinea pig has showered and the updated hygiene value
    displayInfoAlert("Groom", s"You showered your guinea pig!",
      s"Hygiene level is now $hygieneValue." )
  }

  //When player clicks on the 'Comb' button
  def showCombing(): Unit = {
    //Stop both audio [To prevent audio clash]
    showeringSound.stop()
    combingSound.stop()
    //Play combing audio
    combingSound.play()

    //Animate the combing
    groomImage.image = combImage
    animateGrooming(combingAnimation)

    //Update the guinea pig's hygiene value
    val hygieneValue = guineaPig.comb()
    guineaPig.updateHygiene(hygieneValue)

    //Re-display the guinea pig and the hygiene scale (as changes have been made)
    displayGuineaPig(guineaPig)
    displayHygieneScale(guineaPig)

    //Display the alert that guinea pig has been combed and the updated hygiene value
    displayInfoAlert("Groom", s"You combed your guinea pig!",
      s"Hygiene level is now $hygieneValue.")
  }

  //When player clicks on the 'Go Back' button
  def goBack():Unit={
    //Stop both audio
    showeringSound.stop()
    combingSound.stop()
    //Go back to guinea pig's house
    MainApp.showGuineaPigHouse()
  }
}
