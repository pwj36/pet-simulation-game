package ch.makery.game
import ch.makery.game.util.Database
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.Includes._
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import javafx.{scene => jfxs}
import scalafx.scene.image.Image

object MainApp extends JFXApp {
  //Set up the database
  Database.setupDB()

  //To load root layout
  val rootResource = getClass.getResource("view/RootLayout.fxml")
  val loader = new FXMLLoader(rootResource, NoDependencyResolver)
  loader.load();
  val roots = loader.getRoot[jfxs.layout.BorderPane]
  val gameIconStream = getClass.getResourceAsStream("/GameIcon.png")
  val gameIconImg = new Image(gameIconStream)
  stage = new PrimaryStage {
    title = "Pet Guardians Game App"
    scene = new Scene {
      root = roots
    }
    icons.add(gameIconImg)
  }

  //To show welcome page
  def showWelcomePage() = {
    val resource = getClass.getResource("view/WelcomePage.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  //To show guinea pig house
  def showGuineaPigHouse() = {
    val resource = getClass.getResource("view/GuineaPigHouse.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  //To show guinea pig colour options
  def showChooseGuineaPig() = {
    val resource = getClass.getResource("view/ChooseGuineaPig.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  //To show the bathroom
  def showBathroom() = {
    val resource = getClass.getResource("view/Bathroom.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  //To show food store
  def showFoodStore() = {
    val resource = getClass.getResource("view/FoodStore.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  //To show work choices
  def showWorkChoices(): Unit = {
    val resource = getClass.getResource("view/ChooseWork.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  //To show fashion show
  def showFashionShow():Unit= {
    val resource = getClass.getResource("view/FashionShow.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  //To show math competition
  def showMathQuiz(): Unit = {
    val resource = getClass.getResource("view/MathCompetition.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  //To show accessory shop
  def showAccessoryShop(): Unit = {
    val resource = getClass.getResource("view/AccessoryStore.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  //Show the welcome page (starting of the program)
  showWelcomePage()
}