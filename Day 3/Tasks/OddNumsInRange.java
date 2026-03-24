import java.util.*;
public class OddNumsInRange {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int low = sc.nextInt();
        int high = sc.nextInt();
        System.out.println(countOdds(low, high));
    }
    public static int countOdds(int low, int high) {
        return ((high + 1) / 2 - (low / 2));
    }
} 
    

