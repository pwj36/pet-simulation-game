package ch.makery.game.view
import ch.makery.game.MainApp
import scalafxml.core.macros.sfxml
import scalafx.scene.control.{Button, Label}
import ch.makery.game.model.{Carrot, Cucumber, FoodStore, GuineaPig, Tomato}
import scalafx.event.ActionEvent
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.media.{Media, MediaPlayer}
@sfxml
class FoodStoreController(private val firstFoodImage: ImageView,
                          private val firstFoodItem: Button,
                          private val firstFoodPrice: Label,
                          private val firstFoodEnergy: Label,
                          private val secondFoodImage: ImageView,
                          private val secondFoodItem: Button,
                          private val secondFoodPrice: Label,
                          private val secondFoodEnergy: Label,
                          private val thirdFoodImage: ImageView,
                          private val thirdFoodItem: Button,
                          private val thirdFoodPrice: Label,
                          private val thirdFoodEnergy: Label,
                          val moneyLabel: Label) extends DisplayMoney with DisplayInformationAlert {

  private val guineaPig = new GuineaPig()
  //Display the guinea pig's money
  displayMoney(guineaPig)

  //Create instance for food store
  private val foodStore = new FoodStore()
  //Create food
  private val carrot = new Carrot()
  private val cucumber = new Cucumber()
  private val tomato = new Tomato()
  //Add the food into the store
  foodStore.addItemToStore(carrot)
  foodStore.addItemToStore(cucumber)
  foodStore.addItemToStore(tomato)

  //To update about the food's information
  private def updateFood(foodList: FoodStore, index: Int): (String, Image, Double, Int) = {
    //Get food item from the food list
    val food = foodList.accessItem(index)
    //Get the food's information
    val itemName = food.get.name
    val itemImage = food.get.image
    val itemPrice = food.get.price
    val itemEnergy = food.get.energyLevel
    //Return the food's information
    (itemName, itemImage, itemPrice, itemEnergy)
  }

  //To display the food items in the store
  private def displayFoodItems(store: FoodStore, foodIndex: Int,
                               foodItem: Button, foodImage: ImageView,
                               foodPrice: Label, foodEnergy: Label):Unit={
    //Get the accessory information
    val(name, image, price, energy) = updateFood(store, foodIndex)
    //Display information for the food
    foodItem.text = name
    foodImage.image = image
    foodPrice.text =  f"RM%%.2f".format(price)
    foodEnergy.text = energy.toString + " Joules"
  }

  //Display the food items
  displayFoodItems(foodStore, 0, firstFoodItem, firstFoodImage, firstFoodPrice, firstFoodEnergy)
  displayFoodItems(foodStore, 1, secondFoodItem, secondFoodImage, secondFoodPrice, secondFoodEnergy)
  displayFoodItems(foodStore, 2, thirdFoodItem, thirdFoodImage, thirdFoodPrice, thirdFoodEnergy)

  //Audio for when the guinea pig is fed food
  private val guineaPigAudio = new Media(getClass.getResource("/GuineaPigAudio.mp3").toString)
  private val guineaPigSound = new MediaPlayer(guineaPigAudio)

  //Validation when player clicks on the food to purchase it
  def buyFood(event: ActionEvent): Unit = {
    //Determine which button the player click, then get the information for that food
    val playerChoice = event.getSource() match {
      case `firstFoodItem` => foodStore.accessItem(0)
      case `secondFoodItem` => foodStore.accessItem(1)
      case `thirdFoodItem` => foodStore.accessItem(2)
      case _ => None
    }

    playerChoice match {
      case Some(foodItem) =>
        //Update the money the guinea pig has
        val (_, _, _, money,_) = guineaPig.updateData()
        //If the player has enough money
        if(money>=foodItem.price) {
          //Play guinea pig audio to signify guinea pig has been fed
          guineaPigSound.stop()
          guineaPigSound.play()

          //Update the hunger value of the guinea pig
          val name = foodItem.name
          val energyGained = foodItem.energyLevel
          val newHungerValue = guineaPig.feed(energyGained)
          guineaPig.updateHunger(newHungerValue)

          //Update the money of the guinea pig
          val moneyToPay = foodItem.price
          val newMoneyAmount = guineaPig.spendMoney(moneyToPay)
          val convertedMoney = newMoneyAmount.toInt
          guineaPig.updateMoney(convertedMoney)

          //Display information alert that the guinea pig has been fed and the updated hunger value
          displayInfoAlert("Feed Guinea Pig", s"You have fed your guinea pig ${name}!",
            s"Hunger level will increase by $energyGained! Money will decrease by RM $moneyToPay." +
            s"\nHunger level is now $newHungerValue.")

          displayMoney(guineaPig)
        }
        //If the player does not have enough money
        else{
          //Display information alert that there is not enough money
          displayInfoAlert("No Money!", s"Not enough money!",
            s"You do not have enough money!")
        }

      //Invalid item
      case None =>
        println("Invalid selection")
    }
  }

  //If player clicks on 'Go Back' button
  def goBack(): Unit = {
    //Go back to guinea pig house
    MainApp.showGuineaPigHouse()
  }
}

