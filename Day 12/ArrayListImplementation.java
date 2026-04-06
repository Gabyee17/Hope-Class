import java.util.ArrayList;

public class ArrayListImplementation {
    public static void main(String[] args) {

        ArrayList<Integer> arrList = new ArrayList<>();

        arrList.add(null);
        arrList.add(10);
        arrList.add(20);

        System.out.println(arrList);

        arrList.set(0, 0);
        arrList.add(2, 15);
        System.out.println(arrList.get(2));

        System.out.println(arrList);

        arrList.remove(2);
        System.out.println(arrList);
        System.out.println(arrList.contains(10));

        System.out.println(arrList.indexOf(15));
        System.out.println(arrList.lastIndexOf(15));


    }
}
