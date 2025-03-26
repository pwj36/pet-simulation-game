package ch.makery.game.model
import scalafx.beans.property.StringProperty
import ch.makery.game.util.Database
import scalafx.scene.image.Image
import scalikejdbc._

class GuineaPig extends Database {
  //Default values of guinea pig's properties
  val color = new StringProperty("null")
  var hungerScale: LivingScale = new LivingScale(15)
  var hygieneScale: LivingScale = new LivingScale(15)
  var money: Money = new Money(200)
  var accessory: Option[Accessory] = None

  //To generate guinea pig images
  def generateGuineaPigImage(color: String, hygiene: Int): Image={
    //Guinea pig images
    val blackGuineaPigImage = getClass.getResourceAsStream("/BlackGuineaPig.png")
    val dirtyBlackGuineaPigImage = getClass.getResourceAsStream("/DirtyBlackGuineaPig.png")
    val yellowGuineaPigImage = getClass.getResourceAsStream("/YellowGuineaPig.png")
    val dirtyYellowGuineaPigImage = getClass.getResourceAsStream("/DirtyYellowGuineaPig.png")
    val greyGuineaPigImage = getClass.getResourceAsStream("/GreyGuineaPig.png")
    val dirtyGreyGuineaPigImage = getClass.getResourceAsStream("/DirtyGreyGuineaPig.png")

    var image: Image = null
    //If guinea pig's color is "Black"
    if (color == "Black") {
      //If guinea pig has low hygiene (below 20), generate dirty black guinea pig image
      if (hygiene < 20) {
        image = new Image(dirtyBlackGuineaPigImage)
      }
      //Else generate (clean) black guinea pig image
      else {
        image = new Image(blackGuineaPigImage)
      }
    }
    //Else if guinea pig's color is "Yellow"
    else if (color == "Yellow") {
      //If guinea pig has low hygiene (below 20), generate dirty yellow guinea pig image
      if (hygiene < 20) {
        image = new Image(dirtyYellowGuineaPigImage)
      }
      //Else generate (clean) yellow guinea pig image
      else {
        image = new Image(yellowGuineaPigImage)
      }
    }
    //Else guinea pig's color is "Grey"
    else {
      //If guinea pig has low hygiene (below 20), generate dirty grey guinea pig image
      if (hygiene < 20) {
        image = new Image(dirtyGreyGuineaPigImage)
      }
      //Else generate (clean) grey guinea pig image
      else {
        image = new Image(greyGuineaPigImage)
      }
    }
    //Return the guinea pig's image
    image
  }

  //To go to work
  def work(amount: Double): Double = {
    //Going to work will reduce the guinea pig's hygiene and make them hungry
    val newHygiene = hygieneScale.decreaseValue(hygieneScale.level, 5)
    updateHygiene(newHygiene)
    val newHunger = hungerScale.decreaseValue(hungerScale.level, 5)
    updateHunger(newHunger)

    //Guinea pig can earn money when they go to work -> increase money
    val totalMoney = money.increaseValue(money.value,amount)
    //Return total money
    totalMoney
  }

  //To shower guinea pig
  def shower(): Int = {
    //Showering will increase hygiene scale by 15
    hygieneScale.increaseValue(hygieneScale.level, 15)
  }

  //To comb guinea pig
  def comb(): Int = {
    //Combing will increase hygiene scale by 10
    hygieneScale.increaseValue(hygieneScale.level, 10)
  }

  //To feed guinea pig
  def feed(energyLevel: Int):Int = {
    //Each food has different energy level
    //Feeding will increase hunger scale by the energy level of the food
    hungerScale.increaseValue(hungerScale.level, energyLevel)
  }

  //To spend money
  def spendMoney(amount: Double): Double={
    //Going to stores (food or accessory) and purchasing items will cause guinea pig to spend money -> decrease money
    money.decreaseValue(money.value,amount)
  }

  //Save guinea pig's information
  def save(): Unit = {
    DB localTx { implicit session =>
      val accessoryName = accessory match {
        case Some(x) => x.name
        case None => "null"
      }
      sql"""
       insert into guineaPig (color, hungerScale, hygieneScale, money, accessory)
       values (${color.value}, ${hungerScale.level}, ${hygieneScale.level}, ${money.value}, ${accessoryName})
       """.update.apply()
    }
  }

  //Delete guinea pig's information
  def delete(): Unit = {
    DB localTx { implicit session =>
      val accessoryName = accessory match {
        case Some(x) => x.name
        case None => "null"
      }
      sql"""
         DELETE FROM guineaPig
         WHERE color = ${color.value} AND
         hungerScale = ${hungerScale.level}
         AND hygieneScale = ${hygieneScale.level}
         AND money = ${money.value}
         AND accessory = ${accessoryName}
       """.update.apply()
    }
  }

  //Update guinea pig's hunger scale data in the database
  def updateHunger(level: Int): Unit = {
    DB localTx { implicit session =>
      sql"""
          UPDATE guineaPig
          SET hungerScale = $level
          WHERE id = 1
        """.update.apply()
    }
  }

  //Update guinea pig's hygiene scale data in the database
  def updateHygiene(level: Int): Unit = {
    DB localTx { implicit session =>
      sql"""
          UPDATE guineaPig
          SET hygieneScale = $level
          WHERE id = 1
        """.update.apply()
    }
  }

  //Update guinea pig's money in the database
  def updateMoney(amount: Int): Unit = {
    DB localTx { implicit session =>
      sql"""
        UPDATE guineaPig
        SET money = $amount
        WHERE id = 1
      """.update.apply()
    }
  }

  //Update guinea pig's accessory data in the database
  def updateAccessory(accessory: String): Unit = {
    DB localTx { implicit session =>
      sql"""
              UPDATE guineaPig
              SET accessory = $accessory
              WHERE id = 1
            """.update.apply()
    }
  }

  //Update information about the guinea pig from the database
  private def updateFromDatabase(data: (String, Int, Int, Int, String)): Unit = {
    color.value = data._1
    hungerScale = new LivingScale(data._2)
    hygieneScale = new LivingScale(data._3)
    money = new Money(data._4)
    accessory = data._5 match {
      case "Hat" => Some(new Hat())
      case "Ribbon" => Some(new Ribbon())
      case _ => None
    }
  }

  //To update data about the guinea pig
  def updateData(): (String, Int, Int, Double, String) = {
    val guineaPigData = GuineaPig.getGuineaPigData()
    val color = guineaPigData.head._1
    val hunger = guineaPigData.head._2
    val hygiene = guineaPigData.head._3
    val money = guineaPigData.head._4
    val accessory = guineaPigData.head._5
    updateFromDatabase((color, hunger, hygiene, money, accessory))

    //Return the data
    (color, hunger, hygiene, money, accessory)
  }
}

object GuineaPig extends Database {
  //Initialize the database table
  def initializeTable(): Unit = {
    DB autoCommit { implicit session =>
      sql"""
          create table guineaPig (
            id int not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
            color varchar(10),
            hungerScale int,
            hygieneScale int,
            money int,
            accessory varchar(10)
          )
          """.execute.apply()
    }
  }

  //To get guinea pig data from the database
  def getGuineaPigData(): List[(String, Int, Int, Int, String)] = {
    DB readOnly { implicit session =>
      sql""" SELECT * FROM guineaPig """
        .map(rs =>
          (
            rs.string("color"),
            rs.int("hungerScale"),
            rs.int("hygieneScale"),
            rs.int("money"),
            rs.string("accessory")))
        .list
        .apply()
    }
  }
}