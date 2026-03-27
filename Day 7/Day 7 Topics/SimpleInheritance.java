class Parent{
    void display(){
        System.out.println("I am inside the Parent class");
    }
}

class Child extends Parent{
    void display(){
        System.out.println("I am inside Child class");
    }
}


public class SimpleInheritance {
    public static void main(String[] args) {
        System.out.println("Inside Main Function");
        Child c = new Child();
        c.display();
        
    }
}
