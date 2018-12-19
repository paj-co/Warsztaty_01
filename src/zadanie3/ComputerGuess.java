package zadanie3;

import java.util.Scanner;

public class ComputerGuess {

    public static void main(String[] args) {

//        computerGuess();
        computerGuessByPajco();

    }

//------------------------[Universal code for computerGuess() and computerGuessByPajco()]-------------------------------

    static int guess(int min, int max) {
        return (int)(((max - min) / 2) + min);
    }

    static String userResponse() {
        String userInput = null;
        Scanner scan = new Scanner(System.in);
        System.out.println("Odpowiedz: tak lub nie:");
        while (scan.hasNext()) {
            userInput = scan.next();
            if (userInput.equals("tak")) {
                break;
            } else if (userInput.equals("nie")) {
                break;
            } else {
                System.out.println("Niepoprawnie wpisany tekst. Wpisz odpowiedź jeszcze raz.");
            }
            System.out.println("Odpowiedz: tak lub nie:");
        }
        return userInput;
    }
//--------------------------------------[First attempt]----------------------------------------------------------------

    static void computerGuess() {
        System.out.println("Pomyśl liczbę od 0 do 1000 a ja ją zgadnę w max. 10 próbach");
        int min = 0;
        int max = 1000;
        int guess = -1;
        int cheat = 0;
        String userInput;
        int count = 0;

        do {
            System.out.print("Czy jesteś gotowy? ");
        } while (userResponse().equals("nie"));

        while (true) {
            if (cheat == 0) {
                guess = guess(min, max);
                System.out.println("Zgaduję: " + guess);
                count++;
            }
            cheat = 0;
            System.out.println("Czy zgadłem?");
            userInput = userResponse();
            if (userInput.equals("tak")) {
                System.out.println("Wygrałem! Zgadłem w " + count + " próbie.");
                break;
            }
            System.out.println("Czy podałem za dużą wartość?");
            userInput = userResponse();
            if (userInput.equals("tak")) {
                max = guess;
            } else {
                System.out.println("Czy podałem za małą wartość?");
                userInput = userResponse();
                if (userInput.equals("tak")) {
                    min = guess;
                } else {
                    System.out.println("Nie oszukuj");
                    cheat++;
                }
            }

        }

    }
//--------------------------------------[Second attempt]----------------------------------------------------------------

    static String userResponseByPajco() {
        String userInput = null;
        Scanner scan = new Scanner(System.in);
        System.out.print("Odpowiedź trafiłeś, więcej lub mniej: ");
        while(scan.hasNext()) {
            userInput = scan.next();
            if (userInput.equals("trafiłeś")) {
                break;
            } else if (userInput.equals("mniej")) {
                break;
            } else if (userInput.equals("więcej")) {
                break;
            } else {
                System.out.println("Niepoprawnie wpisany tekst. Wpisz odpowiedź jeszcze raz.");
            }
            System.out.print("Odpowiedź trafiłeś, więcej lub mniej: ");
        }
        return userInput;
    }

    static void computerGuessByPajco() {
        System.out.println("Pomyśl liczbę od 0 do 1000 a ja ją zgadnę w max. 10 próbach");
        int min = 0;
        int max = 1000;
        int guess;
        String userInput;
        int count = 0;

        do {
            System.out.println("Czy jesteś gotowy?");
        } while (userResponse().equals("nie"));

        while (true) {
            guess = guess(min, max);
            System.out.println("Zgaduję: " + guess);
            userInput = userResponseByPajco();
            count++;
            if (userInput.equals("trafiłeś")) {
                System.out.println("Wygrałem! Zgadłem w " + count + " próbie.");
                break;
            } else if (userInput.equals("mniej")) {
                max = guess;
            } else if (userInput.equals("więcej")) {
                min = guess;
            }
            if (max - guess <= 1 && guess - min <= 1) {
                System.out.println("Musiałeś się pomylić przy odpowiedziach!\nTo jest niemożliwe by nie była to liczba: " + guess);
                break;
            }
        }
    }

}
