package ch.makery.game.view
import ch.makery.game.MainApp
import ch.makery.game.model.GuineaPig
import scalafxml.core.macros.sfxml
import scalafx.scene.control.{Label, TextField}
import scala.util.Random
@sfxml
class MathCompetitionController(private val answer: TextField,
                                private var number1: Label,
                                private var number2: Label,
                                private var operator: Label) extends DisplayInformationAlert{

  private val guineaPig = new GuineaPig()

  //To randomly generate two numbers and an operator (+ or -)
  private def generateRandomNumbersAndOperator(): (Int, Int, String) = {
    //Randomly generate numbers and operators
    val random = new Random()
    var firstNum = random.nextInt(100)
    var secondNum = random.nextInt(100)
    val randomBoolean = random.nextBoolean()
    val operatorSign = if (randomBoolean == true) "+"  else "-"

    //If operator is minus, make the first number larger so that no negative value answer is required.
    if (operatorSign == "-"){
      while (firstNum<secondNum){
        firstNum = random.nextInt(100)
        secondNum = random.nextInt(100)
      }
    }
    //Return values
    (firstNum, secondNum, operatorSign)
  }

  //To generate a question
  private def generateQuestion(): Unit = {
    //Clear content of text field
    answer.text = ""
    //Generate random numbers
    val (firstNumber, secondNumber, operatorSign) = generateRandomNumbersAndOperator()
    //Display the generated random numbers and operator
    number1.text = firstNumber.toString
    number2.text = secondNumber.toString
    operator.text = operatorSign
  }

  //Generate a question
  generateQuestion()

  //To validate the answer
  def validateAnswer(): Unit = {
    //Update information about the guinea pig's money
    val (_,_,_, money,_) = guineaPig.updateData()

    //Get the player's input
    val playerInput: String = answer.getText()
    //Get the question (random numbers and operators)
    val firstRandomNumber = number1.text.value.toInt
    val secondRandomNumber = number2.text.value.toInt
    val randomOperator = operator.text.value

    //If the submitted answer is not empty and is a numerical value
    if (playerInput.nonEmpty && playerInput.forall(Character.isDigit)) {
      //Parse the player's answer to an integer value
      val playerAnswer = playerInput.toInt
      //Generate correct answer from the question
      val correctAnswer = randomOperator match {
        case "+" => firstRandomNumber + secondRandomNumber
        case "-" => firstRandomNumber - secondRandomNumber
        case _ => 0
      }

      //If player answer is the same as the correct answer
      if (playerAnswer == correctAnswer) {
        //Guinea pig would earn a salary of RM 10
        val totalMoney = guineaPig.work(10)
        val convertedTotalMoney = totalMoney.toInt
        guineaPig.updateMoney(convertedTotalMoney)

        //Display alert that the answer entered was correct
        displayInfoAlert("Results","Correct Answer!",
          "Your answer is correct! Your guinea pig will earn RM 10." +
          s"\nSalary now is: $totalMoney")

        //Generate another question
        generateQuestion()
      }

      //Else the player's answer is incorrect
      else {
        //Display alert that the input answer is incorrect
        displayInfoAlert("Results","Incorrect Answer!",
          s"Your answer is incorrect!. The correct answer is $correctAnswer." +
          s"\nMath Competition has ended.")

        //No salary earned if there is incorrect answer
        //Go back to guinea pig house
        goBackHome()
      }
    }
    //Else player had entered an invalid input (e.g., empty input or non-numerical values)
    else{
      displayInfoAlert("Results", "Invalid input!", s"Please enter an integer value!")
    }
  }

  //To go back to guinea pig house
  def goBackHome():Unit={
    //Display guinea pig house
    MainApp.showGuineaPigHouse()
  }

  //To display the rules for the math competition job
  def showWorkRules():Unit={
    //Display alert
    displayInfoAlert("How to Work?", "WORK RULES: ",
      "1. Answer the math questions displayed." +
        "\n2. You must answer the math questions correctly to gain a salary." +
        "\n3. You will earn RM 10.00 for each correct answer." +
        "\n4. If you input an incorrect answer, the math competition will end.")
  }
}
