interface Test{
    public void show();
}

public class InterfaceImplementation{
    public static void main(String[] args) {
       Test obj=()-> System.out.println("Hello lambda interface");;
        obj.show();
    }
}

interface Test{
    public void show(int a,int b);
}

public class InterfaceWithParameter {
    public static void main(String[] args) {
        Test obj=(a,b)-> System.out.println("Hello lambda interface  "+(a+b));
        obj.show(10,20);
    }
}