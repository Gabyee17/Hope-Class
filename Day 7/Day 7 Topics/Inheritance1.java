class Animal{
    void display(){
        System.out.println("There are some noises");
    }
}

class Dog extends Animal{
    void display(){
        System.out.println("Dog barks");
    }
}

class Cat extends Animal{
    void display(){
        System.out.println("Cat meows");
    }
}

public class Inheritance1 {
    
    public static void main(String[] args){
        Animal A = new Animal();
        Animal d = new Dog();
        Cat c = new Cat();
        d.display();
        c.display();
    }
}
