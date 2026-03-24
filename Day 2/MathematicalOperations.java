import java.util.*;

public class MathematicalOperations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number for value num1: ");
        int num1 = sc.nextInt();
        System.out.print("Enter a number for value num2: ");
        int num2 = sc.nextInt();
        System.out.println("Sum of a+b = " + (num1+num2));
        System.out.println("Sutraction of a-b = " + (num1-num2));
        System.out.println("Multiplication of a*b = " + (num1*num2));
        System.out.println("Division of a/b = " + (num1/num2));
        System.out.println("Modulus of a%b = " + (num1%num2));
        sc.close();
    }
}
