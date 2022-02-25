import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.lang.Character.toUpperCase;
import static java.time.temporal.ChronoUnit.DAYS;

public abstract class Game {

    public static final String COL_GREEN = "\033[1;92m";
    public static final String COL_YELLOW = "\033[1;93m";
    public static final String COL_WHITE = "\033[1;97m";
    public static final String COL_RED = "\033[0;31m";
    public static final String COL_RESET = "\033[0m";
    public static final String CLS = "\033[H\033[2J";

    public static String word;

    public Game() {

    }

    public static void getWord(int mode) {
        int wordNum;
        if (mode == 2) {
            Random rand = new Random();
            wordNum = rand.nextInt(2315);
        } else {
            LocalDate start = LocalDate.parse("25 02 2022", DateTimeFormatter.ofPattern("dd MM yyyy"));
            LocalDate today = LocalDate.now();
            long days = (DAYS.between(start, today) + 254);
            while (days > 2315)
                days -= 2315;

            wordNum = (int) days;
        }

        String data = null;
        try {
            File myObj = new File("answers.txt");
            Scanner myReader = new Scanner(myObj);
            for (int i = 0; i < wordNum; i++) {
                data = myReader.nextLine();
                //System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        word = data;
        //System.out.println("Word after random gen: " + word);
    }

    public static boolean checkIfPossible(String filename, String guess) {
        boolean foundWord = false;
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                //System.out.println(data);
                if (Objects.equals(data, guess)) {
                    foundWord = true;
                    break;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return foundWord;
    }

    public static @NotNull
    String checkGuess(String guess) {
        StringBuilder CurrentGuess = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            char guessChar = guess.charAt(i);
            char wordChar = word.charAt(i);

            if (word.indexOf(guessChar) > -1) {
                if (wordChar == guessChar) {
                    CurrentGuess.append(COL_GREEN).append(toUpperCase(guessChar)).append(" ");
                } else {
                    CurrentGuess.append(COL_YELLOW).append(toUpperCase(guessChar)).append(" ");
                }
            } else {
                CurrentGuess.append(COL_WHITE).append(toUpperCase(guessChar)).append(" ");
            }
            CurrentGuess.append(COL_RESET);
        }
        return CurrentGuess.toString();
    }

    public static void drawGuesses(ArrayList<String> a) {
        for (String s : a) {
            System.out.println(s);
        }
    }

}
