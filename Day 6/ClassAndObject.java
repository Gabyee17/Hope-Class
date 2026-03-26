public class ClassAndObject{
    public static void main(String[] args) {
        ClassAndObject obj=new ClassAndObject();
        obj.Str();
        ClassAndObject.string();
    }
    void Str(){
        System.out.println("Hello World");
    }
    static void string(){
        System.out.println("Welcome to Java");
    }

}