import java.util.*;
public class ArraySign{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of array elements");
        int n = sc.nextInt();
        int[] nums = new int[n];
        System.out.println("Start entering numbers");
        for(int i = 0; i < n; i++){
            nums[i] = sc.nextInt();
        }
        System.out.println(arraySign(nums));
    }
    public static int arraySign(int[] nums) {
        int sign = 1;
        for(int n : nums){
           if(n == 0) return 0;
           if(n < 0) sign = -sign;
        }
        return sign;
    }
}