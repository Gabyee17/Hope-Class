class Animal{
    void display(){
        System.out.println("There are multiple animals here, choose its hierarchy and mentions its sound");
    }
}

class Dog extends Animal{
    void barks(){
        System.out.println("Dog barks!");
    }
}

class Puppy extends Dog{
    void weeps(){
        System.out.println("Puppy cries!");
    }
}

public class MultilevelInheritance {
    public static void main(String[] args){
        Puppy sound = new Puppy();
        sound.display();
        sound.barks();
        sound.weeps();
        
    }
    
}
