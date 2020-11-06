/*
 * EE422C Project 2 (Mastermind) submission by
 * Michael  Normand
 * man2837
 * Slip days used: <0>
 * Fall 2020
 */

package assignment2;

import java.util.Scanner;

/** Class for the individual games to be run on */
public class Game{
	String secret_code;
	int guesses_left;
	
	Game(String secret_code, GameConfiguration config, Scanner scanner){
		this.secret_code = secret_code;
		this.guesses_left = config.guessNumber;
		
		CodeMaster master = new CodeMaster(secret_code, config);
		History history = new History(guesses_left);
		
		runGame(scanner, master, history);
	}
	
	/**
	 * While loop for game iteration
	 */
	public void runGame(Scanner scanner, CodeMaster master, History history) {
		boolean continue_game = true;
		while (continue_game) {
			// new round script
			System.out.println("You have " + guesses_left + " guess(es) left.");
			System.out.println("Enter guess:");
			continue_game = askForGuess(scanner, master, history);
		}
	}
	
	/**
	 * Ask the user for a guess and send this guess to the CodeMaster.
	 * Also determines if user wins or loses.
	 * @return: a string to be printed to the user
	 */
	public boolean askForGuess(Scanner scanner, CodeMaster master, History history) {
		boolean continue_game = true;
		
		// usr_response
		String response = scanner.nextLine();
		
		// valid responses
		if (master.isResponseValid(response)) {
			// convert response to response_msg to be printed
			String response_msg = master.processResponse(response);
			
			// record to history and then print
			history.recordGuess(response_msg, guesses_left);
			System.out.println(response_msg);
			
			// iter since guess is valid
			guesses_left--;
						
			// check if is_winner
			if (response.equals(secret_code)) {
				System.out.println("You win!");
				continue_game = false;
			}
			// check if is_loser
			else if (guesses_left==0) {
				System.out.println("You lose! The pattern was " + secret_code);
				continue_game = false;
			}
			
		}
		// HISTORY response
		else if (response.equals("HISTORY")) {
			history.print();
		}
		// invalid responses
		else {
			System.out.println("INVALID_GUESS");
		}
		
		System.out.println();
		return continue_game;
	}
	
}