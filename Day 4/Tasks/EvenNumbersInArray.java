
public class EvenNumbersInArray
 {
    public static void main(String[] args) {
        System.out.println(findNumbers(new int[]{22, 345, 12, 98, 2456}));
    }
    public static int findNumbers(int[] nums) {
        int count = 0;

        for (int n : nums) {
            if ((n >= 10 && n <= 99) ||
                (n >= 1000 && n <= 9999) ||
                (n == 100000)) {
                count++;
            }
        }

        return count;
    }
}