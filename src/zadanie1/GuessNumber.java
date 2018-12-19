package zadanie1;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GuessNumber {

    public static void main(String[] args) {

        guessNumber();

    }

    public static int getInt() {
        int input = 0;
        Scanner scan = new Scanner(System.in);
        System.out.print("Zgadnij liczbę: ");
        while (scan.hasNext()) {
            try {
                input = scan.nextInt();
                break;
            } catch (InputMismatchException e) {
                scan.next();
                System.out.println("To nie jest liczba.");
                System.out.print("Zgadnij liczbę: ");
            }
        }
        return input;
    }

    static void guessNumber () {
        Random r = new Random();
        int toGuess = r.nextInt(100) + 1;
        int input = 0;

        while (input != toGuess) {
            input = getInt();
            if (input < toGuess) {
                System.out.println("Za mało!");
            } else if (input > toGuess) {
                System.out.println("Za dużo!");
            }
        }
        System.out.println("Zgadłeś!");

    }
}
