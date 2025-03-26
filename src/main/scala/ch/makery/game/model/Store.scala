package ch.makery.game.model

//Abstract class for different types of stores
abstract class Store[T]{
  //List of items in the store
  private var allItems: List[T] = List[T]()

  //To access items from each store
  def accessItem(index: Int): Option[T] = {
    val listLength = allItems.length
    if (index >= 0 && index < listLength) {
      Some(allItems(index))
    } else {
      None
    }
  }

  //To add items to the store
  def addItemToStore(item: T): Unit = {
    allItems ::= item
  }
}