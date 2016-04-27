package mastermind;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {
	
	int blackScore = 0;
	int whiteScore = 0;
	
	char[] code;   //Make by CodeMaker
	int numTries;  //How many tries the CodeBreaker gets
	int triesMade = 0;  //How many tries have been made by CodeBreaker
	char[][] tries;  //The combinations of validCodes that have been guessed by the CodeBreaker
	String[] clues;  //Clues provided by the CodeMaker 
	ArrayList<Character> validCodes = new ArrayList<Character>(Arrays.asList('B', 'G', 'O', 'P', 'R', 'Y'));
	
	//Constructor depends on how many tries the player gets (numTries) and how many orbs per code (length) and what CodeMaker code is used
	public Game (char[] code, int numTries, int length) {
		this.numTries = numTries;
		this.code = code;
		tries = new char[numTries][length];
		clues = new String[numTries];
		code = new char[length];
	}
		
	//Returns true if Code is in right format, else returns false and system outputs a message based on what failure
	public boolean checkCode(char[] code) {
		int counter = 0; 
			
		if(code.length != 4) {
			if(code.length > 4) {
				System.out.println("Code is too long.");
			} else {
				System.out.println("Code is too short.");
			}
			return false;
		}
		for(char c : code) {
			if(!validCodes.contains(c)) {	
				System.out.println(c + " is not a valid color choice.");
			} else {
				counter++;
			}
		}
		if(counter < 4) {  //This means that one of the codes were invalid
			return false;
		}
		return true;
	}
		
	//True if it is a valid try by the CodeBreaker, false if it is not 
	public boolean makeGuess(char[] code) {
		if(!checkCode(code)) {
			return false;
		}
		for(int i = 0; i < triesMade; i++) {     //Repeat guess
			if(code == tries[i]) {      //QUESTION: Does this compare rows or columns? Need it to compare rows
				return false;
			}
		}
		tries[triesMade] = code;  //QUESTION: See 4 lines above
		triesMade++;
		return true;
	}
	
	//Shows the clue for whichever try is asked
	//If that try has not been made yet, just system output the problem
	public String showClue() {
		char[] temp = {0, 0, 0, 0};   //How many of the four are accounted for
		String output = null;
		
		for(int i = 0; i < 4; i++) {
			if(tries[triesMade][i] == code[i]) {
				temp[i] = 1;
				output += 'B';
				blackScore++;
				continue;
			}
		}
		for(int i = 0; i < 4; i++) {
			for(int j = i + 1; j < 4; j++) {
				if(tries[triesMade][i] == code[j] && tries[triesMade][j] != code[j]) {
					temp[j] = 1;
					output += 'W';
					whiteScore++;
				}
			}
		}
		
		clues[triesMade - 1] = output;
		
		if(output == "BBBB") {
			endGame();
			return "Congratulations!";
		}
		
		return "Feedback: " + output;
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
		if(triesMade > 12) {
			System.out.print("You lose. The code was ");
			for(char i : code) {
				System.out.print(code[i]);
			}
			System.out.println(".");
			System.out.println("You totaled " + blackScore + " black pegs and " + whiteScore + " white pegs.");
			return true;
		}
		return false;
	}
	
	//Should give some game stats, some ending bullshit
	private void endGame() {
		System.out.println("You guessed the code in " + triesMade + " tries.");
		System.out.println("You totaled " + blackScore + " black pegs and " + whiteScore + " white pegs.");
		return;
	}
}
