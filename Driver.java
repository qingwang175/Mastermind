package mastermind;

public class Driver {

	public static void main(String[] args) {
		//Need to do IO, just using this array to test preliminarily
		char[] array = {'Y', 'R', 'B', 'O'};
		
		Game game = new Game(array, 12, 4);
	}

}
