package ch.makery.game.model

//Abstract class for metric
//Numeric[T] referred to Stack Overflow (Stack Overflow, 2015) [Reference list in report]
abstract class Metric[T] (var startValue: T)(implicit newValue: Numeric[T]){
  //To increase value of the metric (accepts numeric value of any data type T)
  def increaseValue(currentValue: T, amount: T): T = {
    newValue.plus(currentValue, amount)
  }

  //To decrease value of the metric (accepts numeric value of any data type T)
  def decreaseValue(currentValue: T, amount: T): T = {
    newValue.minus(currentValue, amount)
  }
}