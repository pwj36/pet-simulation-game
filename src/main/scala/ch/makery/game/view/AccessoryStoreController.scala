package ch.makery.game.view
import ch.makery.game.MainApp
import ch.makery.game.model.{AccessoryStore, GuineaPig, Hat, Ribbon}
import scalafxml.core.macros.sfxml
import scalafx.scene.control.{Alert, Button, ButtonType, Label}
import scalafx.event.ActionEvent
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.image.{Image, ImageView}
@sfxml
class AccessoryStoreController(private val firstAccessoryImage: ImageView,
                               private val firstAccessoryItem: Button,
                               private val firstAccessoryPrice: Label,
                               private val secondAccessoryImage: ImageView,
                               private val secondAccessoryItem: Button,
                               private val secondAccessoryPrice: Label,
                               private val removeAccessoryImage: ImageView,
                               val moneyLabel: Label) extends DisplayMoney with DisplayInformationAlert {

  private val guineaPig = new GuineaPig()
  //Display the guinea pig's money
  displayMoney(guineaPig)

  //Create instance for accessory store
  private val accessoryStore = new AccessoryStore()
  //Create accessory items
  private val hat = new Hat()
  private val ribbon = new Ribbon()
  //Add accessory items to the store
  accessoryStore.addItemToStore(hat)
  accessoryStore.addItemToStore(ribbon)

  //Place image for the 'Remove Accessory' button
  private val removeIconImageStream = getClass.getResourceAsStream("/RemoveAccessory.png")
  private val removeIconImage = new Image(removeIconImageStream)
  removeAccessoryImage.image = removeIconImage

  //To update accessory information
  private def updateAccessory(accessoryList: AccessoryStore, index: Int): (String, Image, Double) = {
    //Get accessory from the accessory store's list
    val accessory = accessoryList.accessItem(index)
    //Get information for that accessory
    val itemName = accessory.get.name
    val itemImage = accessory.get.image
    val itemPrice = accessory.get.price
    //Return accessory information
    (itemName, itemImage, itemPrice)
  }

  //To display accessory items in the store
  private def displayAccessoryItems(store: AccessoryStore, accessoryIndex: Int,
                               accessoryItem: Button, accessoryImage: ImageView,
                               accessoryPrice: Label): Unit = {
    //Get the accessory information
    val (name, image, price) = updateAccessory(store, accessoryIndex)
    //Display the information of the accessory
    accessoryItem.text = name
    accessoryImage.image = image
    accessoryPrice.text = f"RM%%.2f".format(price)
  }

  //Display the accessory items
  displayAccessoryItems(accessoryStore, 0, firstAccessoryItem, firstAccessoryImage, firstAccessoryPrice)
  displayAccessoryItems(accessoryStore, 1, secondAccessoryItem, secondAccessoryImage, secondAccessoryPrice)

  //To validate player's action when they want to buy an accessory
  def buyAccessory(event: ActionEvent): Unit = {
    //Update the guinea pig's accessory information
    val (_, _, _, _,accessory) = guineaPig.updateData()

    //Determine which button the player click, then get the information of that accessory
    val playerChoice = event.getSource() match {
      case `firstAccessoryItem` => accessoryStore.accessItem(0)
      case `secondAccessoryItem` => accessoryStore.accessItem(1)
      case _ => None
    }

    playerChoice match {
      case Some(accessoryItem) =>
        val name = accessoryItem.name
        val moneyToPay = accessoryItem.price
        val (_, _, _, money,_) = guineaPig.updateData()

        //If the accessory the user wants to purchase is one that they already have
        if (accessory == name) {
          //Display an alert that the guinea pig is already wearing it.
          displayInfoAlert("Accessory Shop","Already wearing it!", s"Your guinea pig is already wearing this accessory.")
        }

        //Else if they have enough money, let them purchase it
        else if (money >= moneyToPay) {
          //Update the accessory information to that accessory's name
          guineaPig.updateAccessory(name)

          //Deduct money from guinea pig's money and update the data in the database
          val newMoneyAmount = guineaPig.spendMoney(moneyToPay)
          val convertedMoney = newMoneyAmount.toInt
          guineaPig.updateMoney(convertedMoney)

          //Display alert that they have bought the accessory
          displayInfoAlert("Accessory Shop", s"You have bought a ${name}!",
            s"Guinea pig will now be wearing a ${name}!" )

          //Display the updated money
          displayMoney(guineaPig)
        }

        //Else if they do not have enough money
        else {
          //Display not enough money alert
          displayInfoAlert("No Money",s"Not enough money!",
            s"You do not have enough money!")
        }

      //Invalid item
      case None =>
        println("Invalid selection")
    }
  }

  //If player wants to remove the accessory
  def removeAccessory():Unit={
    //Show an alert for confirmation
    //Referred to ScalaFX Dialogs and Alerts (ScalaFx, n.d.) [Reference list in report]
    val removeAccessoryConfirmationAlert = new Alert(AlertType.Confirmation) {
      title = "Accessory shop"
      headerText = "Confirmation: Do you want to remove accessory?"
      contentText = "Clicking 'OK' will mean that you agree to remove the accessory. (Click 'Cancel' to keep the accessory)."
    }
    val confirmationResult = removeAccessoryConfirmationAlert.showAndWait()
    //If player selects 'OK', remove the accessory
    confirmationResult match {
      case Some(ButtonType.OK) => guineaPig.updateAccessory("null")
      case _ => //nothing happens
    }
  }

  //When player clicks on 'Go Back' button
  def goBack(): Unit = {
    //Go back to guinea pig's house
    MainApp.showGuineaPigHouse()
  }
}

