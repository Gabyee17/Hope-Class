import java.util.*;
class MoveZeros{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
    }
    public void moveZeroes(int[] nums) {
        int j = 0; 

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;
            }
        }
    }
}