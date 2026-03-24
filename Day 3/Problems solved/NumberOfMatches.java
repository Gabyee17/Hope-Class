import java.util.*;

class NumberOfMatches {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(numberOfMatches(n));
    }
    public static int numberOfMatches(int n) {
        return n - 1;
    }
}