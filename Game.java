package mastermind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
public class Game {
	
	int blackScore = 0;
	int whiteScore = 0;
	
	char[] code;   //Make by CodeMaker
	int numTries;  //How many tries the CodeBreaker gets
	int triesMade = 0;  //How many tries have been made by CodeBreaker
	char[][] tries;  //The combinations of validCodes that have been guessed by the CodeBreaker
	String[] clues;  //Clues provided by the CodeMaker 
	ArrayList<Character> validCodes = new ArrayList<Character>(Arrays.asList('B', 'G', 'O', 'P', 'R', 'Y'));
	boolean gameWon= false;
	
	//Constructor depends on how many tries the player gets (numTries) and how many orbs per code (length) and what CodeMaker code is used
	public Game (char[] code, int numTries, int length) {
		this.numTries = numTries;
		code = new char[length];
		this.code = code;
		tries = new char[numTries][length];
		clues = new String[numTries];
	}
		
	//Returns true if Code is in right format, else returns false and system outputs a message based on what failure
	public boolean checkCode(char[] trycode) {
		int counter = 0; 
			
		if(trycode.length != code.length) {
			if(trycode.length > code.length) {
				System.out.println("INVALID INPUT: Code is too long.");
			} else {
				System.out.println("INVALID INPUT: Code is too short.");
			}
			return false;
		}
		for(char c : trycode) {
			if(!validCodes.contains(c)) {	
				System.out.println("INVALID INPUT: "+c + " is not a valid color choice.");
			} else {
				counter++;
			}
		}
		if(counter < code.length) {  //This means that one of the codes were invalid
			return false;
		}
		return true;
	}
		
	//True if it is a valid try by the CodeBreaker, false if it is not 
	public boolean makeGuess(char[] trycode) {
		if(!checkCode(trycode)) {
			return false;
		}
		for(int i = 0; i < triesMade; i++) {     //Repeat guess
			if(trycode == tries[i]) {      //QUESTION: Does this compare rows or columns? Need it to compare rows ( I think yes)
				return false;
			}
		}
		tries[triesMade] = trycode; 
		triesMade++;
		return true;
	}
	
	//Shows the clue for whichever try is asked
	//If that try has not been made yet, just system output the problem
	public String showClue() {
		char[] temp = {0, 0, 0, 0};   //How many of the four are accounted for
		int bMatch = 0;
		int wMatch = 0;
		for(int i = 0; i < code.length; i++) {
			if(tries[triesMade - 1][i] == code[i]) {
				temp[i] = 1;
				bMatch++;
				this.blackScore++;
				continue;
			}
		}
		for(int i = 0; i < code.length; i++) {
			for(int j = 0; j < code.length; j++) {
				if(tries[triesMade - 1][i] == code[j] && temp[j] == 0) {
					temp[j] = 1;
					wMatch++;
					this.whiteScore++;
				}
			}
		}
		
		clues[triesMade - 1] = bMatch + " black peg(s) and " + wMatch + " white peg(s).";   //This is what is put into the clues
		
		if(bMatch == 4) {
			endGame();
			gameWon = true;
			return "Congratulations!";
		}
		
		return "Result: " + bMatch + " black peg(s) and " + wMatch + " white peg(s).";
	}
	
	public String showOldClue(int whichTry) {
		if(whichTry == 0) {
			return "Feedback for 1st try: " + clues[whichTry];
		} else if (whichTry == 1) {
			return "Feedback for 2nd try: " + clues[whichTry];
		} else if (whichTry == 2) {
			return "Feedback for 3rd try: " + clues[whichTry];
		}
		
		return "Feedback for " + (whichTry + 1) + "th try: " + clues[whichTry];
	}
	
	public boolean isGameOver() {
		if(triesMade >= numTries) {
			System.out.print("You lose. The code was ");
			for(int i = 0; i<code.length;i++) {
				System.out.print(code[i]);
			}
			System.out.println(".");
			//System.out.println("You totaled " + blackScore + " black pegs and " + whiteScore + " white pegs.");
			return true;
		}
		return false;
	}
	
	//Should give some game stats, some ending stuff
	private void endGame() {
		System.out.println("You guessed the code in " + triesMade + " tries.");
		System.out.println("You totaled " + blackScore + " black pegs and " + whiteScore + " white pegs.");
		return;
	}
	
	public void makeNewGame(){
		Random random = new Random();
		int index=0;
		for (int i=0; i<code.length;i++){
			index = random.nextInt(validCodes.size());
			code[i]=validCodes.get(index);
		}
		
	}
}
