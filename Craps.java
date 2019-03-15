/*
 * Author: Suki Sahota
 * Description: Craps Game
 */
import java.util.*;
public class Craps {
    // Constant for number of games
    public static final int GAMES = 10000;
    public static void main(String[] args) {
        play();
    }
    
    public static void play() {
        description();
        Scanner kb = new Scanner(System.in);
        Random rand = new Random();
        int numberOfWin = 0, numberOfL = 0, thePoint = 0, comeOutRoll = 0;
        boolean playAgain = true;
        // While loop to control if user plays again
        while (playAgain) {
            for (int i = 0; i < GAMES; i++) {
                int dice1 = rand.nextInt(6) + 1;
                int dice2 = rand.nextInt(6) + 1;
                comeOutRoll = dice1 + dice2;
                String x = winOrLose(comeOutRoll);
                if (x.equalsIgnoreCase("win")) {
                    numberOfWin++;
                }
                else if (x.equalsIgnoreCase("lose")) {
                    numberOfL++;
                }
                else if (x.equalsIgnoreCase("thePoint")) {
                    thePoint = comeOutRoll;
                }
                // If you don't win/lose on first roll, keep rolling
                String y = keepRolling(thePoint);
                if (y.equalsIgnoreCase("seven")) {
                    numberOfL++;
                }
                else if (y.equalsIgnoreCase("thePoint")) {
                    numberOfWin++;
                }
            }
            double probability = winProbability(numberOfWin, numberOfL);
            System.out.println("In the simulation we:\n\twon " + numberOfWin + " times\n\tlost " + numberOfL + " times,\n\tfor a winning probability of " + probability);
            System.out.println();
            numberOfWin = 0; numberOfL = 0;
            System.out.println("Type 'yes' if you would like to play another game, or no to stop.");
            String answer = kb.nextLine();
            if (!answer.equalsIgnoreCase("yes")) {
                playAgain = false;
            }   
        }
        System.out.println("Have a nice day. GoodBye");
    }
    
    // Come out roll returns a string
    public static String winOrLose(int comeOutRoll) {
        String x = "";
        if (comeOutRoll == 7 || comeOutRoll == 11) {
            x = "win";
        }
        else if (comeOutRoll == 2 || comeOutRoll == 3 || comeOutRoll == 12) {
            x = "lose";
        }
        else {
            x = "thePoint";
        }
        return x;
    }
    
    public static void description() {
        System.out.println("\nThe computer will play a series of Craps games for you. \nHere are the rules of the game: ");
        System.out.println("Two six sided die are rolled, where");
        System.out.println("the come out roll equals the sum of the first two die in a Craps round.");
        System.out.println("A come out roll of 7 or 11 automatically wins");
        System.out.println("A come out roll of 2, 3, or 12 automatically losses");
        System.out.println("A come out roll of 4, 5, 6, 8, 9, or 10 becomes 'The Point'.");
        System.out.println("If the player gets 'The Point', he/she will keep playing by rolling the dice until he/she gets a 7 or the point. If the point is rolled before a 7, then the player wins the bet. If a 7 is rolled before the point, then the player loses.");
        System.out.println("We will simulate 10,000 games\nLets start playing!\n");
    }
    
    // keepRolling method returns a string
    public static String keepRolling(int thePoint) {
        Random rand = new Random();
        String y = "";
        int dice1 = rand.nextInt(6) + 1;
        int dice2 = rand.nextInt(6) + 1;
        int roll = dice1 + dice2;
        while (!(roll == 7 || roll == thePoint)) {
            dice1 = rand.nextInt(6) + 1;
            dice2 = rand.nextInt(6) + 1;
            roll = dice1 + dice2;
        }
        if (roll == 7) {
            y = "seven";
        }
        else if (roll == thePoint) {
            y = "thepoint";
        }
        return y;
    }
    
    // Probability is in decimal form
    public static double winProbability(double wins, double losses) {
        double probability = wins / (wins + losses);
        return probability;    
    }
}
