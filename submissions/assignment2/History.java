/*
 * EE422C Project 2 (Mastermind) submission by
 * Michael  Normand
 * man2837
 * Slip days used: <0>
 * Fall 2020
 */
package assignment2;

/** Manager for response_msgs to guesses already made */
public class History{
	private int total_num_guesses;
	private String[] guess_list;

	History(int total_num_guesses){
		this.total_num_guesses = total_num_guesses;
		guess_list = new String [total_num_guesses];
	}
	
	/**
	 * Record the system response_msg to the array guess_list
	 * @param response_msg is the text that is returned to the user per guess
	 * @param guesses_left is the number of guesses left
	 */
	public void recordGuess(String response_msg, int guesses_left) {
		int idx = total_num_guesses - guesses_left;
		guess_list[idx] = response_msg;
	}
	
	public void print() {
		for (String response_msg: guess_list) {
			if (response_msg==null) {
				break;
			}
			System.out.println(response_msg);
		}
	}
}