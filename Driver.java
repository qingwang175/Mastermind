/**
 * @author Qing Wang (qw2328)
 * @author Katelyn Ge (kbg488)
 * EE422C Bonus Assignment
 */

package mastermind;

import javax.swing.JOptionPane;

public class Driver {

	public static void main(String[] args) {
		char[] array = {};
		char[] input = {};
		Game game = null;
		int choice = 0;
		int gamesWon = 0;
		int gamesLost = 0;
		String answer = "";
		String result = "";
		String codemaker = "";
		boolean running = true;
		boolean newGame = true;
		try {
			while (running) {
				if (newGame) { // start a new game
					//First jOptionPane
					choice = JOptionPane.showConfirmDialog(null,
							"You have a certain number of guesses to figure out the secret code or you lose the game. Are you ready to play?",
							null, JOptionPane.YES_NO_OPTION);
					running = (choice == JOptionPane.YES_OPTION);   //Game only starts if you choose yes
					newGame = false;               
					if (running) {
						while (true) {         //Keep going until you get a real usable input
							answer = JOptionPane.showInputDialog("Insert number of maximum tries: ");
							if (isNumber(answer) && Integer.parseInt(answer) > 0) {
								break;
							} 
							else if (answer == null || answer.length() <=0){
								running = false;
								break;
							}
							else {
								System.out.println("INVALID: insert a positive integer");    //Error message; you will reinput
							}
						}
						if (!running){break;}
						while (true) {           //Again, keep looping the JOptionPane until you get the real input
							result = JOptionPane.showInputDialog("Insert number of pegs: ");
							if (isNumber(result) && Integer.parseInt(answer) > 0) {
								break;
							} 
							else if (result == null || result.length() <=0){
								running = false;
								break;
							}
							else {
								System.out.println("INVALID: insert a positive integer");          //Error message and reinput
							}
						}
						if (!running){break;}
						choice = JOptionPane.showConfirmDialog(null,
								"You have the option to create your own code for another player to guess. Click Yes if you would like to. Click No if you would like a random code generated for you. ",
								null, JOptionPane.YES_NO_OPTION);     //If you choose yes, you input your own code
						if (choice == JOptionPane.NO_OPTION) {     //Generate random code for you out of possible choices (duplicates allowed)
							System.out.println("Generating secret code..." + "\n");
							game = new Game(Integer.parseInt(answer), Integer.parseInt(result));
							game.makeNewGame();
							// TEST: printing secret code to check if things are
							// working as they should
							//for (int i = 0; i < game.code.length; i++) {
							//	System.out.print(game.code[i]);
							//}
						} else {
							game = new Game(Integer.parseInt(answer), Integer.parseInt(result));     //You generating your own code
							while (true) {
								codemaker = JOptionPane
										.showInputDialog("Input code (must be made out of B, G, O, P, R, Y): ");
								array = codemaker.toCharArray();
								if (game.checkCode(array)) {     //This checks if this cod is valid
									break;
								}
							}
							game.code = array;   //Code is accepted
						}
					}
					else{
						break;
					}
				}
				// Make guesses
				int triesLeft = game.numTries - game.triesMade;
				if (triesLeft > 1) {     //You are told how many tries you have left before each guess
					System.out.println("You have " + (triesLeft) + " guesses left.");
				} else {
					System.out.println("You have " + (triesLeft) + " guesses left.");
				}
				while (true) {  
					System.out.println("What is your next guess?");
					System.out.println("Type in " + (game.code.length) + " characters for your guess and press OK.");
					answer = JOptionPane.showInputDialog("Enter guess: ");
					input = answer.toCharArray();      //Guess is recorded
					if (game.makeGuess(input)) {      //If this is a valid guess, pass through this loop
						System.out.print(answer + "--> ");
						break;
					} else {
						System.out.println();
					}
				}
				System.out.println(game.showClue() + "\n");   //You are told how many pegs you hit

				if (game.gameWon || game.isGameOver()) {     //You either win or lose the game by this point
					if (game.gameWon) {
						gamesWon++;     //Record wins
					} else {
						gamesLost++;  //Record losses
					}
					System.out.println("Games won: " + gamesWon + ", Games lost: " + gamesLost);      //Tells you your current record
					choice = JOptionPane.showConfirmDialog(null, "Would you like to play again?", null,     //Yes to reset game, or terminate program
							JOptionPane.YES_NO_OPTION);
					running = (choice == JOptionPane.YES_OPTION);
					newGame = true;
				}
			}
		} catch (NullPointerException e) {
			return;
		} catch (NumberFormatException s) {
			return;
		}
	}
	
	//Checks if the number works formatwise
	public static boolean isNumber(String str) {
		try {
			int x = Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

}
