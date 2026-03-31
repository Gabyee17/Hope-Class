abstract class Animal{
    void eat(){
        System.out.println("Eating...");
    }
}

class Dog extends Animal{
    void sound(){
        System.out.println("Dog barks...");
    }
}



class Cat extends Animal{
    void sound(){
        System.out.println("Cat meows...");
    }
}

public class Abstraction {
    public static void main(String[] args) {
        Dog a = new Dog();
        a.eat();
        a.sound();


    }
}
