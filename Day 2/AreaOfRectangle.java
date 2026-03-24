import java.util.*;
public class AreaOfRectangle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Length: ");
        int length = sc.nextInt();
        System.out.print("Enter Width: ");
        int width = sc.nextInt();  
        System.out.print("Area of rectangle is: "+" "+ (length*width));
    }
}
