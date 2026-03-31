class Animal{
    void sound(){
        System.out.println("The animal makes a sound!");
    }
}

class Dog extends Animal{
    void sound(){
        System.out.println("The dog barks!");
    }
}

class Cat extends Animal{

    void sound(){
        System.out.println("The cat meows!");
    }
}

public class polymorphismImplementation {
    public static void main(String[] args) {
        Animal animal;
        animal = new Animal();
        animal.sound();
        animal = new Cat();
        animal.sound();
        animal = new Dog();
        animal.sound();
    }
    
}
