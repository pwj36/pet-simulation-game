package ch.makery.game.view
import ch.makery.game.model.GuineaPig
import scalafx.scene.image.{Image, ImageView}

trait DisplayAccessory{
  val accessoryImage: ImageView

  //Accessory images
  val hat = getClass.getResourceAsStream("/HatAccessory.png")
  val ribbon = getClass.getResourceAsStream("/RibbonAccessory.png")

  //To display the accessory
  def displayAccessory(guineaPig: GuineaPig): Unit = {
    //Update the information about the guinea pig's accessory
    val (_, _,_, _, accessory) = guineaPig.updateData()

    //If guinea pig's information states that it has a "Hat" as an accessory
    if (accessory == "Hat") {
      //Accessory image would be the hat
      val hatImage = new Image(hat)
      accessoryImage.image = hatImage
    }
    //Else if guinea pig's information states that it has a "Ribbon" as an accessory
    else if(accessory == "Ribbon"){
      //Accessory image would be the ribbon
      val ribbonImage = new Image(ribbon)
      accessoryImage.image = ribbonImage
    }
    //Else that means the guinea pig's information states that it does not have an accessory
    else {
      //Accessory image would be null
      accessoryImage.image = null
    }
  }
}