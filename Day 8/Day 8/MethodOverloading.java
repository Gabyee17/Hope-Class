public class MethodOverloading {
    static void add(int x, int y){
        System.out.println(x + y);
    }
    static void add(int x, int y, int z){
        System.out.println(x + y + z);
    }
    public static void main(String[] args) {
        add(10, 20);
        add(1, 3, 6);
    }
}
