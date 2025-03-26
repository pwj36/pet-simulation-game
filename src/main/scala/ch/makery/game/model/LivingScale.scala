package ch.makery.game.model

//LivingScale is a subclass of abstract class Metric
class LivingScale(_startValue: Int) extends Metric[Int](_startValue){
  var level: Int = startValue
  private val maxLevel: Int = 100
  private val minLevel:Int = 0

  //Override Metric increaseValue function
  override def increaseValue(currentValue: Int, amount: Int): Int = {
    //If level is lesser than maximum level call increase value function from the superclass (Metrics)
    if(level<maxLevel){
      level = super.increaseValue(currentValue, amount)

      //If level has exceeded maximum level, set it to 100
      if(level>maxLevel){
        level = 100
      }
    }
    //Else level is >= to maxLevel
    else{
      //Set level to 100
      level = 100
    }
    //Return level
    level
  }

  //Override Metric decreaseValue function
  override def decreaseValue(currentValue: Int, amount: Int): Int = {
    //Call decrease value from superclass (Metrics)
    level = super.decreaseValue(currentValue, amount)

    //If after decreasing, the level is lesser than the minimum value
    if (level < minLevel) {
      //Set level to 0
      level = 0
    }
    //Return level
    level
  }
}