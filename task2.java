import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class WordCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Word Counter");


        System.out.println("Enter '1' to input text, '2' to provide a file:");
        int choice = Integer.parseInt(scanner.nextLine());

        String text = "";


        if (choice == 1) {
            System.out.println("Enter the text:");
            text = scanner.nextLine();
        } else if (choice == 2) {
            System.out.println("Enter the file path:");
            String filePath = scanner.nextLine();
            text = readTextFromFile(filePath);
        } else {
            System.out.println("Invalid choice. Exiting.");
            System.exit(0);
        }
        String[] words = text.split("[\\s\\p{Punct}]+");

        int wordCount = 0;

        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            if (!word.isEmpty()) {
                wordCount++;

                wordFrequency.put(word.toLowerCase(), wordFrequency.getOrDefault(word.toLowerCase(), 0) + 1);
            }
        }

        System.out.println("Total words: " + wordCount);


        System.out.println("Word Frequency:");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        scanner.close();
    }

    private static String readTextFromFile(String filePath) {

        return "";
    }
}
