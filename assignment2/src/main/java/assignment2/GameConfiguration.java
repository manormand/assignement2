package assignment2;

public class GameConfiguration {
    final int guessNumber;
    final String[] colors;
    final int pegNumber;

    GameConfiguration(int guessNumber, String[] colors, int pegNumber) {
        this.guessNumber = guessNumber;
        this.colors = colors;
        this.pegNumber = pegNumber;
    }
}
