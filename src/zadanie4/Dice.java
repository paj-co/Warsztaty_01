package zadanie4;

import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Dice {

    public static void main(String[] args) {

//--------------------------------------------[Testowanie poprawności podanego kodu]------------------------------------
//        String str = "6";
//        String str2 = "6+1";
//        String str3 = "+1";
//        String str4 = "D";
//        String str5 = "6D";
//        String str6 = "6D-1";
//        String str7 = "6Da-1";
//        String str8 = "2D6  +10";
//        System.out.println(diceCodeVerifier(str));
//        System.out.println(diceCodeVerifier(str2));
//        System.out.println(diceCodeVerifier(str3));
//        System.out.println(diceCodeVerifier(str4));
//        System.out.println(diceCodeVerifier(str5));
//        System.out.println(diceCodeVerifier(str6));
//        System.out.println(diceCodeVerifier(str7));
//        System.out.println(diceCodeVerifier(str8));
//--------------------------------------------[Uruchomienie programu]---------------------------------------------------
        System.out.println(diceRoll());



    }
//--------------------------------------------[Weryfikacja podanego przez użytkownika kodu]-----------------------------
    static boolean diceCodeVerifier(String diceCode) {
        boolean result = false;
        StringTokenizer diceTokens = new StringTokenizer(diceCode, "D+-");
        int howManyTokens = diceTokens.countTokens();

        char[] diceCodeToCharTab = diceCode.toCharArray();

        if ((diceCode.length() - diceCode.replaceAll("D", "").length() != 1) || howManyTokens > 3) {
            System.out.println("Niepoprawny format kodu kostki.");
            result = false;
        } else if (howManyTokens == 1 && diceCodeToCharTab[diceCodeToCharTab.length - 1] == 'D') {
            System.out.println("Niepoprawny format kodu kostki.");
            result = false;
        } else if (diceCode.contains("D-") || diceCode.contains("D+") || (diceCode.length() == 1 && diceCode.charAt(0) == 'D')) {
            System.out.println("Niepoprawny format kodu kostki.");
            result = false;
        } else {
            while (diceTokens.hasMoreTokens()) {
                try {
                    Integer.parseInt(diceTokens.nextToken());
                    result = true;
                } catch (NumberFormatException e) {
                    System.out.println("Niepoprawny format kodu kostki.");
                    return false;
                }
            }
        }
        return result;
    }
//--------------------------------------------[Pojedyńczy rzut kostką]--------------------------------------------------
    static int dice(int side) {
        Random r = new Random();
        return r.nextInt(side) + 1;
    }
//--------------------------------------------[Główna funkcja]----------------------------------------------------------
    static int diceRoll() {
        String userInput = null;
        int sum = 0;
        int oneDiceTossResult;
        int x;
        int y;
        int z;

        Scanner scan = new Scanner(System.in);
        System.out.println("Wprowadź kod rzutu kostki w formacie xDy+z np. 2D10+10 - gdzie: \n - x to liczba rzutów, " +
                "\n - Dy oznacza kostkę o y ilości ścian, \n - z to liczba jaką należy dodać " +
                "lub odjąć (ze znakiem \"-\") do wyniku rzutu kośćmi");
        System.out.println("Jeśli chcesz wyjść z programu wpisz \"quit\"");

        while (true) {
            userInput = scan.nextLine();
            if (userInput.equals("quit")) {
                return 0;
            }

            if (diceCodeVerifier(userInput)) {
                break;
            }
            System.out.println("Podaj kod kostki jeszcze raz: ");

        }

        StringTokenizer diceTokens = new StringTokenizer(userInput, "D+-");
        int diceTokenCount = diceTokens.countTokens();

        if (diceTokenCount == 3) {
            x = Integer.parseInt(diceTokens.nextToken());
            y = Integer.parseInt(diceTokens.nextToken());
            z = Integer.parseInt(diceTokens.nextToken());

            for (int i = 0; i < x; i++) {
                oneDiceTossResult = dice(y);
                System.out.println("Rzut " + (i + 1) + ": " + oneDiceTossResult);
                sum += oneDiceTossResult;//sum += dice(y);
            }

            if (userInput.contains("+")) {
                System.out.println("Dodaję do sumy rzutów " + z + ".");
                sum += z;
            } else {
                System.out.println("Odejmuję od sumy rzutów " + z + ".");
                sum -= z;
            }
        } else if (diceTokenCount == 2 && (userInput.contains("+") || userInput.contains("-"))) {
            y = Integer.parseInt(diceTokens.nextToken());
            z = Integer.parseInt(diceTokens.nextToken());

            oneDiceTossResult = dice(y);
            System.out.println("Rzut " + 1 + ": " + oneDiceTossResult);
            if (userInput.contains("+")) {
                System.out.println("Dodaję do sumy rzutów " + z + ".");
                sum = oneDiceTossResult + z;
            } else {
                System.out.println("Odejmuję od sumy rzutów " + z + ".");
                sum = oneDiceTossResult - z;
            }
        } else if (diceTokenCount == 2 && !(userInput.contains("+") || userInput.contains("-"))) {
            x = Integer.parseInt(diceTokens.nextToken());
            y = Integer.parseInt(diceTokens.nextToken());

            for (int i = 0; i < x; i++) {
                oneDiceTossResult = dice(y);
                System.out.println("Rzut " + (i + 1) + ": " + oneDiceTossResult);
                sum += oneDiceTossResult;
            }
        } else if (diceTokenCount == 1) {
            y = Integer.parseInt(diceTokens.nextToken());
            oneDiceTossResult = dice(y);
            System.out.println("Rzut " + 1 + ": " + oneDiceTossResult);
            sum += oneDiceTossResult;
        }
        System.out.println("Wynik Twojego rzutu to: " + sum);
        return sum;
    }
}
