package mastermind;

import java.util.Arrays;

import javax.swing.JOptionPane;

import java.io.Console;

public class Driver {

	public static void main(String[] args) {
		// Need to do IO, just using this array to test preliminarily
		char[] array = { 'Y', 'R', 'B', 'O' };
		char[] input={};
		Game game = new Game(array, 12, 4);
		int choice = 0;
		String answer="";
		String result="";
		boolean running = true;
		boolean newGame = true;
		boolean correctInput = false;
		while (running) {
			if (newGame) {	//start a new game
				choice = JOptionPane.showConfirmDialog(null,
						"You have a certain number of guesses to figure out the secret code or you lose the game. Are you ready to play?",
						null, JOptionPane.YES_NO_OPTION);
				running = (choice == JOptionPane.YES_OPTION);
				newGame = false;
				if (running) {
				answer = JOptionPane.showInputDialog("Insert number of maximum tries: ");
				result = JOptionPane.showInputDialog("Insert number of pegs: ");
					System.out.println("Generating secret code..."+"\n");
					game = new Game(array, Integer.parseInt(answer), Integer.parseInt(result));
					game.makeNewGame();
					//TEST: printing secret code to check if things are working as they should
					for (int i=0; i<game.code.length;i++){
						System.out.print(game.code[i]);
					}
				}
			}
			//make guesses
			System.out.println("You have "+(game.numTries-game.triesMade)+" guesses left");
			while (!correctInput) {
				System.out.println("What is your next guess?");
				System.out.println("Type in the characters for your guess and press OK.");
				answer = JOptionPane.showInputDialog("Enter guess: ");
				input = answer.toCharArray();
				if (game.makeGuess(input)) {
					correctInput = true;
					break;
				}
				else{
					correctInput = false;
					System.out.println();
				}
			}
			correctInput = false;				//reset code check
			game.showClue();				//this isn't showing clues. why not?
			if ((game.blackScore+game.whiteScore)==0){
				result = "No pegs";
			}
			else{
				result = game.blackScore+" black pegs, "+ game.whiteScore+ "white pegs";
			}
			System.out.println("\n"+answer+"--> Result: "+result+"\n");
			
			if (game.isGameOver()){
				choice = JOptionPane.showConfirmDialog(null,
						"Would you like to play again?",
						null, JOptionPane.YES_NO_OPTION);
				running = (choice == JOptionPane.YES_OPTION);
			}
		}
	}
	

}
