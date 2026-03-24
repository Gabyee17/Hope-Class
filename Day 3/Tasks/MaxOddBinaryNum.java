import java.util.*;
class MaxOddBinaryNum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(maximumOddBinaryNumber(s));
    }
    public static String maximumOddBinaryNumber(String s) {
        StringBuilder sb = new StringBuilder();
        int oneCount = 0;
        for(char ch : s.toCharArray()){
            if(ch == '1') oneCount++;
        }
        for(int i = 0; i < oneCount - 1; i++){
            sb.append('1');
        }
        for(int j = 0; j < s.length() - oneCount ; j++){
            sb.append('0');
        }
        sb.append('1');
        return sb.toString();
    }
}