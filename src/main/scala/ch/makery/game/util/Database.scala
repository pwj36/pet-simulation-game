package ch.makery.game.util
import scalikejdbc._
import ch.makery.game.model.GuineaPig
//Referred to address app code (Chin, 2023) [Reference list in report]
//Apache derby database
trait Database {
    val derbyDriverClassName = "org.apache.derby.jdbc.EmbeddedDriver"
    val dbURL = "jdbc:derby:myDB;create=true;";
    Class.forName(derbyDriverClassName)
    ConnectionPool.singleton(dbURL, "me", "mine")
    implicit val session = AutoSession
}

object Database extends Database {
  //To setup database
  def setupDB():Unit = {
    if (!hasDBInitialize) {
      GuineaPig.initializeTable()
    }
  }

  //Determine if database has been initialized
  def hasDBInitialize(): Boolean = {
    DB getTable "guineaPig" match {
      case Some(x) => true
      case None => false
    }
  }
}

