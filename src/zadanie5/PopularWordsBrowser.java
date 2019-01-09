package zadanie5;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class PopularWordsBrowser {

    public static void main(String[] args) {

        String pathToDirectory = "src/zadanie5/";
        String rpPL = "https://www.rp.pl/";
        String rpPLCssSelector = "h3.teaser__title";
        String onetPL = "https://www.onet.pl/";
        String onetPLCssSelector = "span.title";
        getPopularWords(rpPL, rpPLCssSelector, pathToDirectory);
//        getPopularWords(onetPL, onetPLCssSelector, pathToDirectory);
        filterPopularWords(pathToDirectory);
        sortPopularWords(pathToDirectory);
    }

    static void getPopularWords (String pathToInternetSite, String cssSelector, String pathToDirectory) {
        String fileName = "popular_words.txt";
        Path pathToBaseFile = Paths.get(pathToDirectory + fileName);
        ArrayList<String> outWrite = new ArrayList<>();
        if (!Files.exists(pathToBaseFile)) {
            try {
                Files.createFile(pathToBaseFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Connection connect = Jsoup.connect(pathToInternetSite);
        try {
            Document document = connect.get();
            Elements links = document.select(cssSelector);

            for (Element elem : links) {
                StringTokenizer elemTokens = new StringTokenizer(elem.text(), " :,.?!;\"\'„”");
                while (elemTokens.hasMoreTokens()) {
                    String element = elemTokens.nextToken();
                    if (element.length() > 1) {//if (element.length() >= 3)
                        outWrite.add(element);
                    }
                }
            }
            Files.write(pathToBaseFile, outWrite, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String[] excludedWords() {
        return new String[] {"ale", "albo", "bardzo", "bez", "będą", "będzie", "być", "bywa", "bywają", "co", "chcą", "chce",
                "czy", "dalej", "duży", "duże", "dużej", "dla", "do", "jest", "już", "kiedy", "który", "która", "którzy",
                "lepsza", "lepszy", "lepsi", "mieć", "mam", "masz", "mają", "ma", "mogą", "mogę", "możesz", "może", "na",
                "nam", "nami", "nie", "niż", "od", "odpowie", "oraz", "po", "ponieważ", "poza", "przed", "są", "się",
                "to", "we", "wszystkie", "wszystko", "za", "zajmą", "zależą", "ze", "że"};
    }

    static void filterPopularWords (String pathToDirectory) {
        String fileName = "popular_words.txt";
        Path pathToBaseFile = Paths.get(pathToDirectory + fileName);
        Path pathToNewFile = Paths.get(pathToDirectory + "filtered_" + fileName);

        if (!Files.exists(pathToNewFile)) {
            try {
                Files.createFile(pathToNewFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            for (String line : Files.readAllLines(pathToBaseFile)) {
                FileWriter outWrite = new FileWriter(pathToNewFile.toString(), true);
                int counter = 0;
                //Sprawdzenie czy w pliku, do którego zapisujemy jest już takie słowo
                for (String isMoreSameLine : Files.readAllLines(pathToNewFile)) {

                    if (line.toLowerCase().equals(isMoreSameLine.toLowerCase())) {
                        //int couter pozwala sprawdzić czy podane słowo występuje już w pliku, do którego chcemy zapisać bieżące słowo
                        counter++;
                    }
                }
                if (counter == 0) {
                    //Spcjalna zasada dla słowa PO (skróconej nazwy jednej z większych parti opozycyjnych) - ponieważ słowo "po" występuje w słowniku słów wykluczonych
                    if (line.equals("PO")) {
                        outWrite.append(line + "\n");
                    } else {
                        int count = 0;
                        for (String forbidenWord : excludedWords()) {
                            if (line.toLowerCase().equals(forbidenWord)) {
                                count++;
                            }
                        }
                        if (count == 0) {
                            outWrite.append(line + "\n");
                        }
                    }
                }
                outWrite.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void sortPopularWords (String pathToDirectory) {
        String fileName = "popular_words.txt";
        Path pathToBaseFile = Paths.get(pathToDirectory + fileName);
        Path pathToNewFile = Paths.get(pathToDirectory + "filtered_" + fileName);
        Path pathToSortedFile = Paths.get(pathToDirectory + "sorted_" + "filtered_" + fileName);

        if (!Files.exists(pathToSortedFile)) {
            try {
                Files.createFile(pathToSortedFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ArrayList<String> sorted = new ArrayList<>();
        try {
            for (String filteredWord : Files.readAllLines(pathToNewFile)) {
                int howManySameWords = 0;
                for (String allWords : Files.readAllLines(pathToBaseFile)) {

                    if (filteredWord.toLowerCase().equals(allWords.toLowerCase())) {
                        howManySameWords++;
                    }
                }
                if (howManySameWords < 10) {
                    sorted.add("0" + howManySameWords + " " + filteredWord);
                } else {
                    sorted.add(howManySameWords + " " + filteredWord);
                }
            }
            Collections.sort(sorted);
            Files.write(pathToSortedFile, sorted);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
