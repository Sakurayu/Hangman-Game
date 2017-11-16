import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by Zhenyu Zhou on 11/14/2017.
 */
public class MovieName {

    static String originalName = "";
    static String movie = "";

    static String guessedLetter = "";
    static String wrongLetters = "";
    static boolean hasWon = false;

    public static void main(String[] args) throws FileNotFoundException{
        File file = new File("movies.txt");
        Scanner scan = new Scanner(file);
        int count = 0;
        List<String> movieTable = new ArrayList<>();

        while(scan.hasNextLine()){
            String line = scan.nextLine();
            count++;
            movieTable.add(line);
        }

        int random = (int)(Math.random() * count);
        originalName = movieTable.get(random);
        System.out.println("Random movie is: " + originalName);
        movie = originalName.toLowerCase();
        displayMovieTitle();

        int num = 0;

        while (num != 10 & !hasWon) {
            System.out.println("You have guesses (" + num + ") wrong letters: " + wrongLetters);
            System.out.println("Guess a letter:");
            Scanner sc = new Scanner(System.in);
            String input = sc.next().toLowerCase();
            if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                num = checkGuess(input, num);
            } else {
                System.out.println("Guess one single letter only");
            }
        }
        if (hasWon) {
            System.out.println("You Win!");
        } else {
            System.out.println("You Lose! The movie's name is " + originalName);
        }
    }

    public static int checkGuess(String s, int num) {
        if (movie.contains(s)) {
            if (!guessedLetter.contains(s)) {
                guessedLetter += s;
            }
        } else {
            if (!wrongLetters.contains(s)) {
                wrongLetters += s + " ";
                num++;
            }
        }
        displayMovieTitle();
        return num;
    }

    public static void displayMovieTitle() {
        String display = "";
        for(int i = 0; i < movie.length(); i++) {
            char c = movie.charAt(i);
            if (Character.isLetter(originalName.charAt(i))) {
                if (guessedLetter.indexOf(c) >= 0) display += originalName.charAt(i);
                else display += "-";
            } else {
                display += originalName.charAt(i);
            }
        }
        System.out.println("You are guessing movie: " + display);
        if (display.equals(originalName)) {
            hasWon = true;
        }
    }
}
