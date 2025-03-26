package ch.makery.game.model

//Money is a subclass of abstract class Metric
class Money(_startValue: Double) extends Metric[Double](_startValue) {
  var value: Double = startValue

  //Override Metric increaseValue function
  override def increaseValue(currentValue: Double, amount: Double): Double = {
    //Call increaseValue function from superclass (Metric)
    value = super.increaseValue(value, amount)
    //Return value
    value
  }

  //Override Metric decreaseValue function
  override def decreaseValue(currentValue: Double, amount: Double): Double = {
    //Call decreaseValue function from superclass (Metric)
    value = super.decreaseValue(value, amount)
    //Return value
    value
  }
}