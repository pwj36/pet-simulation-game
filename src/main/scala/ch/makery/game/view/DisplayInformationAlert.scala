package ch.makery.game.view
import scalafx.scene.control.Alert
import scalafx.scene.control.Alert.AlertType

trait DisplayInformationAlert {
  //To display information alert
  //Referred to ScalaFX Dialog and Alerts (ScalaFx, n.d.) [Reference list is in report]
  def displayInfoAlert(titleContent: String, headerContent: String, textContent: String): Unit = {
    val alert = new Alert(AlertType.Information) {
      title = titleContent
      headerText = headerContent
      contentText = textContent
    }
    alert.showAndWait()
  }
}