package mastermind;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {
	
	char[] code;   //Make by CodeMaker
	int numTries;  //How many tries the CodeBreaker gets
	int triesMade = 0;  //How many tries have been made by CodeBreaker
	char[][] tries;  //The combinations of validCodes that have been guessed by the CodeBreaker
	char[][] clues;  //Clues provided by the CodeMaker 
	ArrayList<Character> validCodes = new ArrayList<Character>(Arrays.asList('B', 'G', 'O', 'P', 'R', 'Y'));
	
	//Constructor depends on how many tries the player gets (numTries) and how many orbs per code (length) and what CodeMaker code is used
	public Game (char[] code, int numTries, int length) {
		this.numTries = numTries;
		this.code = code;
		tries = new char[numTries][length];
		clues = new char[numTries][length];
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
		tries[i] = code;
		triesMade++;
		return true;
	}
}
