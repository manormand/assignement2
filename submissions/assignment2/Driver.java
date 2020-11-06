/*
 * Mastermind
 * Jan 20, 2020
 */
/*
 * EE422C Project 2 (Mastermind) submission by
 * Michael  Normand
 * man2837
 * Slip days used: <0>
 * Fall 2020
 */
package assignment2;

import java.util.Arrays;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
    	// init  game config and code generator
    	//new GameConfiguration(12, new String[]{"B","G","O","P","R","Y"}, 4)
    	int num_guesses = 12;
    	String[] colors = new String[] {"B","G","O","P","R","Y"};
    	int num_pegs = 4;
    	
        GameConfiguration config = new GameConfiguration(num_guesses, colors, num_pegs);
        SecretCodeGeneratorMock genMock = new SecretCodeGeneratorMock(Arrays.asList("OPGB", "RBBY"));
//        SecretCodeGenerator generator = new SecretCodeGenerator(config);
        
        start(true, config, genMock);
    }

    public static void start(Boolean isTesting, GameConfiguration config, SecretCodeGenerator generator) {
    	// init Scanner for usr input
    	Scanner scanner= new Scanner(System.in);
    	
    	// print greeting
    	System.out.println("Welcome to Mastermind.");
    	
    	// loop games until new_game is false
    	boolean loop_games = true;
    	while (loop_games) {
    		// new game script
    		System.out.println("Do you want to play a new game? (Y/N):");
    		
    		// parse usr input for new game and run if specified
    		String usr_new_game_response = scanner.nextLine();
    		if (usr_new_game_response.equals("Y")) {
    			newGame(isTesting, config, generator, scanner);
    		}
    		else if (usr_new_game_response.equals("N")){
    			loop_games = false;
    		}
    		else {
    			System.out.println("Please input either a 'Y' or 'N',");
    		}
    	
    	}
    	
    	// close scanner
    	scanner.close();
    }
    
    /**
     * Create a new Game obj and run a game
     * @param isTesting
     * @param config
     * @param generator
     * @param scanner is the Scanner obj
     */
    public static void newGame(Boolean isTesting, GameConfiguration config, SecretCodeGenerator generator, Scanner scanner) {
    	// init secret code
    	String secret_code = generator.getNewSecretCode();
    	
    	// game init script
    	if (isTesting) {
    		System.out.println("Secret code: " + secret_code);
    	}
    	System.out.println();
    	
    	new Game(secret_code, config, scanner);
    }
}
