import java.util.Scanner;
public class StringBasic {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.nextLine();
        int length = input.length();
        System.out.println("Length of the string: " + length);
        String upperCase = input.toUpperCase();
        System.out.println("Uppercase: " + upperCase);
        String lowerCase = input.toLowerCase();
        System.out.println("Lowercase: " + lowerCase);
        System.out.print("Enter a substring to check: ");
        String substring = sc.nextLine();
        boolean containsSubstring = input.contains(substring);
        System.out.println("Contains '" + substring + "': " + containsSubstring);
        String[] words = input.split(" ");
        System.out.println("Words in the string:");
        for (String word : words) {
            System.out.println(word);
        }
        sc.close();
    }
}