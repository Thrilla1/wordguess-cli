import java.util.*;

public class Main extends Game {



    public static void main(String[] args) {
        boolean correct = false;
        ArrayList<String> prevGuesses = new ArrayList<>();
        String guess;
        Scanner sc = new Scanner(System.in);
        String wordListFile = "answers.txt";
        String possibleListFile = "possible.txt";
        int noGuesses = 0;

        getWord(1);
        //System.out.println(COL_YELLOW + "word: " + word + COL_RESET);
        while (noGuesses < 6) {
            boolean guessExists = false;
            System.out.println(CLS);
            drawGuesses(prevGuesses);
            System.out.println("\n\n(" + noGuesses + "/6 guesses used)\nEnter guess: ");
            guess = sc.next().toLowerCase(Locale.ROOT);

            if (checkIfPossible("answers.txt", guess)) {
                if (guess.equals(word)) {
                    correct = true;
                    prevGuesses.add(checkGuess(guess));
                    drawGuesses(prevGuesses);
                }
                //System.out.println("Word exists in answers list");
                guessExists = true;
            } else if (checkIfPossible("possible.txt",guess)) {
                //System.out.println("Word exists in possible list");
                guessExists = true;
            } else {
                System.out.println(COL_RED + "Not a word" + COL_RESET);
            }

            if (guessExists) {
                prevGuesses.add(checkGuess(guess));
                noGuesses++;
            }

            if (correct)
                break;

        }

        if (correct)
        {
            System.out.println("\n" + COL_GREEN + "CORRECT GUESS! The word was: " + word.toUpperCase(Locale.ROOT) + COL_RESET);
            System.out.println("It took you " + noGuesses + " guesses.");
        }


       /* if (possibleList.contains(guess))
        {
            prevGuesses.add(checkGuess(guess));
            if (answerList.contains(guess))
            {
                System.out.println("Correct! The word was " + word.toUpperCase(Locale.ROOT) + "." );
            } else
            {
                checkGuess(guess);
            }
        } else {
            System.out.println("Not a word.");
        }*/
    }


}
