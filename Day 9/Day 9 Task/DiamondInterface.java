class Parent_A{
    void print() {
        System.out.println("This is Parent class A");
    }
}
interface B{
    void print();
}

interface C{
    void print();
}

class Child_D extends Parent_A implements B, C{

    public void print(){
        System.out.println("This is Child class D");
    }

    void printParent(){
        super.print();
    }
}

public class DiamondInterface{
    public static void main(String[] args){
        Child_D child=new Child_D();
        child.printParent(); 
        child.print();       
    }
}
