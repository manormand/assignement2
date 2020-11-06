/*
 * EE422C Project 2 (Mastermind) submission by
 * Michael  Normand
 * man2837
 * Slip days used: <0>
 * Fall 2020
 */
package assignment2;

/** Handles codes from the usr and the game itself */
public class CodeMaster{
	private String secret_code;
	private GameConfiguration config;
	
	/**
	 * @param secret_code is the code the user must guess
	 */
	CodeMaster(String secret_code, GameConfiguration config){
		this.secret_code = secret_code;
		this.config = config;
	}
	
	/**
	 * Checks if the response has the required length and consists of correct colors
	 * @param response is the usr input from the terminal
	 * @return true if valid, false if not
	 */
	public boolean isResponseValid(String response) {
		String[] colors = config.colors;
		int num_pegs = config.pegNumber;
		
		// check if length is correct
		if (response.length() != num_pegs) {
			return false;
		}
		
		// check if all colors are valid
		boolean is_valid;
		char[] response_colors = response.toCharArray();
		for (char response_color: response_colors) {
			// reset is_valid
			is_valid = false;
			
			// check through all config colors
			for (String config_color: colors) {
				if (config_color.charAt(0) == response_color) {
					is_valid = true;
					break;
				}
			}
			
			// if no matches made, response is not valid
			if (!is_valid) {
				return false;
			}
			// else run through rest of chars in response
		}
		// if loop is cleared, response is valid
		return true;
	}
	
	/**
	 * Respond to the usr's guess
	 * @param response is the input from terminal
	 * @return the message to be printed back to terminal
	 */
	public String processResponse(String response) {
		// init key_pegs
		int black_pegs = 0;
		int white_pegs = 0;
		char[] secret_code_chars = secret_code.toCharArray();
		char[] response_chars = response.toCharArray();
		
		// check for correct color and place (black_pegs)
		for (int i = 0; i < response.length(); i++) {
			if (response_chars[i] == secret_code_chars[i]) {
				black_pegs++;
				
				// change char so it wont be counted twice
				secret_code_chars[i] = 'x';
				response_chars[i] = 'y';
			}
		}
		
		// check for correct color
		for (int i = 0; i < response.length(); i++) {
			for (int j = 0; j < secret_code_chars.length; j++) {
				if (response_chars[i] == secret_code_chars[j]) {
					white_pegs++;
					
					// change char so it wont be counted twice
					secret_code_chars[j] = 'x';
					response_chars[i] = 'y';
					// break out of secret_code_chars loop incase of duplicates
					break;
				}
			}
		}
		
		// concatenate msg
		String key_pegs_msg = black_pegs + "b_" + white_pegs + "w";
		String response_msg = response + " -> " + key_pegs_msg;
		return response_msg;
	}
}