package mastermind;

import javax.swing.JOptionPane;

public class Driver {

	public static void main(String[] args) {
		// Need to do IO, just using this array to test preliminarily
		char[] array = { 'Y', 'R', 'B', 'O' };
		char[] input={};
		Game game = new Game(array, 12, 4);
		int choice = 0;
		String answer="";
		String result="";
		String codemaker="";
		boolean running = true;
		boolean newGame = true;
		try {
			while (running) {
				if (newGame) {	//start a new game
					choice = JOptionPane.showConfirmDialog(null,
							"You have a certain number of guesses to figure out the secret code or you lose the game. Are you ready to play?",
							null, JOptionPane.YES_NO_OPTION);
					running = (choice == JOptionPane.YES_OPTION);
					newGame = false;
					if (running) {
					while(true){
					answer = JOptionPane.showInputDialog("Insert number of maximum tries: ");
					if (isNumber(answer) && Integer.parseInt(answer) > 0){
						break;
					}
					else{
						System.out.println("INVALID: insert a positive integer");
					}
					}
					while (true){
					result = JOptionPane.showInputDialog("Insert number of pegs: ");
					if (isNumber(result) && Integer.parseInt(answer) > 0){
						break;
					}
					else{
						System.out.println("INVALID: insert a positive integer");
					}
					}
					choice = JOptionPane.showConfirmDialog(null, "You have the option to create your own code for another player to guess. Click Yes if you would like to. Click No if you would like a random code generated for you. ",
							null, JOptionPane.YES_NO_OPTION);
					if (choice == JOptionPane.NO_OPTION){
						System.out.println("Generating secret code..."+"\n");
						game = new Game(array, Integer.parseInt(answer), Integer.parseInt(result));
						game.makeNewGame();
						//TEST: printing secret code to check if things are working as they should
						for (int i=0; i<game.code.length;i++){
							System.out.print(game.code[i]);
						}
					}
					else{
						game = new Game (array, Integer.parseInt(answer), Integer.parseInt(result));
						while(true){
						codemaker = JOptionPane.showInputDialog("Input code (must be made out of B, G, O, P, R, Y): ");
						array = codemaker.toCharArray();
						if (game.checkCode(array)){
							break;
						}
						}
						game.code = array;
					}
					}
				}
				//make guesses
				int triesLeft = game.numTries - game.triesMade;
				if(triesLeft > 1) {
					System.out.println("You have "+(triesLeft)+" guesses left");
				} else {
					System.out.println("You have "+(triesLeft)+" guesses left");
				}
				while (true) {
					System.out.println("What is your next guess?");
					System.out.println("Type in "+ (game.code.length) +  " characters for your guess and press OK.");
					answer = JOptionPane.showInputDialog("Enter guess: ");
					input = answer.toCharArray();
					if (game.makeGuess(input)) {
						System.out.print(answer+ "-->");
						break;
					}
					else{
						System.out.println();
					}
				}
				System.out.println("\n" + game.showClue() + "\n");				//this isn't showing clues. why not?
				
				if (game.gameWon || game.isGameOver()){
					if (game.gameWon){
						game.addScore(true);
					}
					else{
						game.addScore(false);
					}
					System.out.println("Games won: "+ game.gamesWon+ ", Games lost: "+game.gamesLost);
					choice = JOptionPane.showConfirmDialog(null,
							"Would you like to play again?",
							null, JOptionPane.YES_NO_OPTION);
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
	
	public static boolean isNumber(String str)  
	{  
	  try  
	  {  
	    int x = Integer.parseInt(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}

}
