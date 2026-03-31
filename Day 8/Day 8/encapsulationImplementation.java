class Encapsulation {
    private int a;   // encapsulated variable

    // Constructor
    Encapsulation(int a){
        this.a = a;
    }

    // Getter
    int getA(){
        return a;
    }

    // Setter
    void setA(int a){
        this.a = a;
    }

    void display(){
        System.out.println("Implementation of Encapsulation");
    }
}

public class encapsulationImplementation {
    public static void main(String[] args) {
        
        Encapsulation obj = new Encapsulation(10);

        obj.display();

        // Access using getter
        System.out.println("Value of a: " + obj.getA());

        // Modify using setter
        obj.setA(20);
        System.out.println("Updated value of a: " + obj.getA());
    }
}