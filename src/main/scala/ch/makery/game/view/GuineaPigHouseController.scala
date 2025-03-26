package ch.makery.game.view
import ch.makery.game.MainApp
import scalafxml.core.macros.sfxml
import scalafx.scene.control.{Label, ProgressBar}
import ch.makery.game.model.GuineaPig
import scalafx.scene.image.{Image, ImageView}
@sfxml
class GuineaPigHouseController(private val feedImage: ImageView,
                               private val groomImage: ImageView,
                               private val workImage: ImageView,
                               val guineaPigImage: ImageView,
                               val accessoryImage: ImageView,
                               val moneyLabel: Label,
                               val hungerLevel: ProgressBar,
                               val hygieneLevel: ProgressBar)
  extends DisplayGuineaPig with DisplayMoney with DisplayHungerScale with DisplayHygieneScale with DisplayAccessory{

  val guineaPig = new GuineaPig()
  //Display guinea pig and its details (accessory, money, hunger scale and hygiene scale)
  displayGuineaPig(guineaPig)
  displayAccessory(guineaPig)
  displayMoney(guineaPig)
  displayHungerScale(guineaPig)
  displayHygieneScale(guineaPig)

  //Generate image for 'Feed' button
  private val feedIconStream= getClass.getResourceAsStream("/FeedIcon.png")
  private val feedIconImage = new Image(feedIconStream)
  feedImage.image = feedIconImage

  //Generate image for 'Groom' button
  private val groomIconStream = getClass.getResourceAsStream("/GroomIcon.png")
  private val groomIconImage = new Image(groomIconStream)
  groomImage.image = groomIconImage

  //Generate image for 'Work' button
  private val workIconStream = getClass.getResourceAsStream("/WorkIcon.png")
  private val workIconImage = new Image(workIconStream)
  workImage.image = workIconImage

  //If player clicks on 'Feed' button
  def feed(): Unit = {
    //Show the food store
    MainApp.showFoodStore()
  }

  //If player clicks on 'Groom' button
  def groom(): Unit = {
    //Show the bathroom
    MainApp.showBathroom()
  }

  //If player clicks on 'Work' button
  def showWork(): Unit = {
    //Show the work options
    MainApp.showWorkChoices()
  }

  //If player clicks on the 'Buy Accessory' button
  def buyAccessory(): Unit = {
    //Show the accessory shop
    MainApp.showAccessoryShop()
  }
}
