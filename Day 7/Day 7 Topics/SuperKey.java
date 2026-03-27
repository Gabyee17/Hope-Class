class superExample1{
    superExample1(){
        System.out.println("Called in SuperExample1 class");
    }
}

class superExample2{
    superExample2(){
        System.out.println("Called in SuperExample2 class");
    }

    void reject(){
        System.out.println("I haven't called you, why have you come in?");
        super.hello();
    }
}

public class SuperKey {
    public static void main(String[] args){
        superExample2 s2 = new superExample2();
        s2.reject();
    }
    
}
