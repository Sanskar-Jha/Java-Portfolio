import java.util.Scanner;
import java.util.Random;

public class RockPaperScissor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Takes input

        System.out.println("Start Game");
        Random rn = new Random();
        int randNum = rn.nextInt(1, 4);

        /*
         * rock = 1
         * paper = 2
         * siccsor = 3
         */

        int userCount = 0;
        int computerCount = 0;

        int i = 1;
        while (i <= 5) {
            int userVal = sc.nextInt();
            System.out.println(randNum);

            if (userVal == randNum) {
                System.out.println("tie");
                continue;
            }

            switch (userVal) {
                case 1:
                    System.out.println("Rock");
                    if (randNum == 2) {
                        System.out.println("you lose");
                        computerCount++;
                        System.out.println("User count is " + userCount + " || " + "Computer count is " + computerCount);
                    } else {
                        System.out.println("you win");
                        userCount++;
                        System.out.println("User count is " + userCount + " || " + "Computer count is " + computerCount);
                    }
                    break;

                case 2:
                    System.out.println("Paper");
                    if (randNum == 3) {
                        System.out.println("you lose");
                        computerCount++;
                        System.out.println("User count is " + userCount + " || " + "Computer count is " + computerCount);
                    } else {
                        System.out.println("you win");
                        userCount++;
                        System.out.println("User count is " + userCount + " || " + "Computer count is " + computerCount);
                    }
                    break;

                case 3:
                    System.out.println("siccsor");
                    if (randNum == 1) {
                        System.out.println("you lose");
                        computerCount++;
                        System.out.println("User count is " + userCount + " || " + "Computer count is " + computerCount);
                    } else {
                        System.out.println("you win");
                        userCount++;
                        System.out.println("User count is " + userCount + " || " + "Computer count is " + computerCount);
                    }
                    break;

                default:
                    System.out.println("Invalid number");
            }
            i++;
        }

        System.out.println("----------Final---------");
        if (userCount == computerCount) {
            System.out.println("Tie");
        } else if (userCount > computerCount) {
            System.out.println("You won the match");
        } else {
            System.out.println("Computer won the match");
        }
    }
}