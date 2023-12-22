import java.util.Scanner;

class DifferentNumberGame {
    public static void main(String[] args) {
        int secretNumber = generateRandomNumber(1, 100);
        int maxAttempts = 7;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Different Number Guessing Game!");
        System.out.println("Think of a number between 1 to 100.");

        for (int attempts = 1; attempts <= maxAttempts; attempts++) {
            System.out.print("Enter your guess: ");
            int userGuess = getUserInput(scanner);

            if (userGuess == secretNumber) {
                System.out.println("Congratulations! You guessed the number!\nYou win!");
                break;
            }

            displayHint(userGuess, secretNumber, attempts, maxAttempts);
        }

        System.out.println("The correct number was: " + secretNumber);
        System.out.println("Thanks for playing!");
        scanner.close();
    }

    private static int generateRandomNumber(int lowerBound, int upperBound) {
        return (int) (Math.random() * (upperBound - lowerBound + 1)) + lowerBound;
    }

    private static int getUserInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next(); // consume the invalid input
        }
        return scanner.nextInt();
    }

    private static void displayHint(int userGuess, int secretNumber, int attempts, int maxAttempts) {
        if (userGuess > secretNumber) {
            System.out.println("Your guess is too high. You have " + (maxAttempts - attempts) + " tries left.");
        } else {
            System.out.println("Your guess is too low. You have " + (maxAttempts - attempts) + " tries left.");
        }

        if (attempts == maxAttempts) {
            System.out.println("Your attempts have ended. You lose!");
        }
    }
}
