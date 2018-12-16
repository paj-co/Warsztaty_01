package zadanie2;

import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class lottoSimulator {

    public static void main(String[] args) {

        lotto(getSixDiffInts());

    }

    static int[] getSixDiffInts() {
        int[] sixInts = new int[6];
        int possibleNumber;
        int count;

        Scanner scan = new Scanner(System.in);

        for (int i = 0; i < sixInts.length; i++) {
            System.out.print("Podaj liczbę: ");

            while (scan.hasNext()) {
                try {
                    count = 0;
                    possibleNumber = scan.nextInt();

                    if (possibleNumber >= 1 && possibleNumber <= 49) {

                        for (int param : sixInts) {

                            if (possibleNumber == param) {
                                count++;
                            }
                        }
                        if (count == 0) {
                            sixInts[i] = possibleNumber;
                            break;
                        } else {
                            System.out.print("Liczba była już podana.\nPodaj inną liczbę: ");
                        }
                    } else {
                        System.out.print("Podana liczba nie mieści się w przedziale od 1 do 49.\nPodaj liczbę jeszcze raz: ");
                    }

                } catch (InputMismatchException e) {
                    scan.next();
                    System.out.print("Niepoprawny format. Podaj liczbę jeszcze raz: ");
                }
            }
        }
        Arrays.sort(sixInts);
        System.out.println("Podane przez Ciebie liczby to: ");
        System.out.println(Arrays.toString(sixInts));
        return sixInts;
    }

    static void lotto(int[] getSix) {
        int count = 0;
        Integer[] tab49 = new Integer[49];
        for (int i = 0; i < tab49.length; i++) {
            tab49[i] = i + 1;
        }

        Collections.shuffle(Arrays.asList(tab49));
        Integer[] onlySixShuffleInts = Arrays.copyOf(tab49, 6);
        Arrays.sort(onlySixShuffleInts);
        System.out.println("Program wylosował następujące liczby: ");
        System.out.println(Arrays.toString(onlySixShuffleInts));

        for (int i = 0; i < getSix.length; i++) {
            for (int j = 0; j < onlySixShuffleInts.length; j++) {
                if (getSix[i] == tab49[j]) {
                    count++;
                }
            }
        }
        if (count >= 3) {
            System.out.println("Trafiłeś " + count + "!");
        } else {
            System.out.println("Trafiłeś tylko " + count + " liczb.");
        }
    }

}
