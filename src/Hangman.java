import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman
{
    static int wrongCount = 0;
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Welcome to Hangman! \n1 or 2 players?");
        String players = keyboard.nextLine();
        String word;

        if(players.equals("1"))
        {
            Scanner scanner = new Scanner(new File("C:\\Users\\priti\\IdeaProjects\\textfile.txt"));

            List<String> words = new ArrayList<>();
            while(scanner.hasNext())
            {
                words.add(scanner.nextLine());
            }

            Random rand = new Random();
            word = words.get(rand.nextInt(words.size()));
        }
        else
        {
            System.out.println("Player 1, please enter your word: ");
            word = keyboard.nextLine();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("Ready for player 2! Good luck!");
        }

        // System.out.println(word);
        System.out.println("Word length = " + word.length());

        List<Character> playerGuesses = new ArrayList<>();

        while(true)
        {
            System.out.println("Player Guesses: " + playerGuesses);
            printHangedMan(wrongCount);

            if (wrongCount >= 6)
            {
                System.out.println("You lost!");
                System.out.println("The right word is " + word);
                break;
            }

            printWordState(word, playerGuesses);
            getPlayerGuess(keyboard, word, playerGuesses);

            printHangedMan(wrongCount);

            if (wrongCount >= 6)
            {
                System.out.println("You lost!");
                System.out.println("The right word is " + word);
                break;
            }

            if (printWordState(word, playerGuesses)) {
                System.out.println("You won!");
                break;
            }

            System.out.println("Want to guess the word? (yes/no): ");
            if(keyboard.nextLine().equals("yes"))
            {
                System.out.println("Enter word, please: ");
                String newInput = keyboard.nextLine();
                if (newInput.equals(word))
                {
                    System.out.println("You won!");
                    break;
                }
                else
                {
                    System.out.println("Nope! Try again.");
                    wrongCount++;
                }
            }
            else
            {
                System.out.println("");
            }
        }
    }

    public static void printHangedMan(int wrongCount)
    {
        System.out.println("_______");
        System.out.println(" |   | ");

        if(wrongCount >= 1)
        {
            System.out.println(" O");
        }

        if(wrongCount >= 2)
        {
            System.out.print("\\ ");
            if(wrongCount >= 3)
            {
                System.out.println("/");
            }
            else
            {
                System.out.println("");
            }
        }

        if(wrongCount >= 4)
        {
            System.out.println(" |");
        }

        if(wrongCount >= 5)
        {
            System.out.print("/ ");
            if(wrongCount >= 6)
            {
                System.out.println("\\");
            }
            else
            {
                System.out.println("");
            }
        }
        System.out.println("");
    }

    public static boolean getPlayerGuess(Scanner keyboard, String word, List<Character> playerGuesses)
    {
        System.out.println("Please enter a letter:");
        String letterGuess = keyboard.nextLine();
        playerGuesses.add(letterGuess.charAt(0));

        if(word.contains(letterGuess))
        {
            System.out.println("You're getting close! Keep going :)");
        }
        else
        {
            System.out.println("Sorry, wrong letter! Try again!");
            wrongCount++;
        }

        return (word.contains(letterGuess));
    }
    public static boolean printWordState(String word, List<Character> playerGuesses)
    {
        int correctCount = 0;
        for(int i = 0; i < word.length(); i++)
        {
            if(playerGuesses.contains(word.charAt(i)))
            {
                System.out.print(word.charAt(i));
                correctCount++;
            }
            else
            {
                System.out.print("-");
            }
        }
        System.out.println();

        return (word.length() == correctCount);
    }
}


