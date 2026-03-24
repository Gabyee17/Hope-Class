import java.util.*;
public class NumberOfOneBits {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(hammingWeight(n));
    }
    public static int hammingWeight(int n) {
        return Integer.bitCount(n);
    }
}