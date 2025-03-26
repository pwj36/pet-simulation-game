package ch.makery.game.view

trait DisplayGameInstructions extends DisplayInformationAlert {
  //To display instructions
  def displayInstructions():Unit={
    displayInfoAlert("Game Instructions", "How to Play?",
      "Welcome to Pet Guardians!" +
        "\n1. In pet guardians you would be taking care of a guinea pig!" +
        "\n2. Guinea pigs can get hungry, so do buy them food from the food store when the hunger scale gets low." +
        "\n3. Guinea pigs have to maintain good hygiene level. Maintain the hygiene level by grooming them." +
        "\n4. To earn money, guinea pigs can go for work. Make sure your guinea pig has a good hygiene level before going to work. " +
        "Going to work with a low hygiene level may result in a lesser salary! " +
        "However, guinea pigs may reduce hygiene and get hungry after work. So do remember to groom and feed them after work!" +
        "\n5. You can also buy accessories for your guinea pig to wear from the accessory store.")
  }
}